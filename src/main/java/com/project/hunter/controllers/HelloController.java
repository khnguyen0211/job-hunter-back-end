package com.project.hunter.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.MyClass;
import com.project.hunter.MyOs;
import com.project.hunter.Results;


@RestController()
public class HelloController {

    // @GetMapping()
    // public RedirectView getLicenseApi() {
    // return new RedirectView("/swagger-ui/index.html");
    // }

    @GetMapping()
    public ResponseEntity<List<Results>> getMethodName() {
        return ResponseEntity.ok().body(getResult());
    }

    public List<Results> getResult() {
        String classString = readClassFile(
                "src\\main\\java\\com\\project\\hunter\\files\\dhcp_fingerprints.conf");
        String[] arrString = classString.split("\n");
        List<MyClass> myClasses = new ArrayList<>();
        List<MyOs> myOses = new ArrayList<>();
        for (int i = 0; i < arrString.length; i += 3) {
            int n = arrString[i].length();
            String classId = arrString[i].substring(1, n - 1);
            String description = arrString[i + 1].split("=")[1];
            String members = arrString[i + 2];
            int lowerBound = Integer.parseInt(members.split("=")[1].split("-")[0]);
            int upperBound = Integer.parseInt(members.split("=")[1].split("-")[1]);
            MyClass classA = new MyClass(classId, description, lowerBound, upperBound);
            myClasses.add(classA);
        }
        String osString =
                readOsFile("src\\main\\java\\com\\project\\hunter\\files\\dhcp_fingerprints.conf");
        String[] osStringArr = osString.split("\n");
        for (int i = 0; i < osStringArr.length;) {
            int c = 0;
            int n = osStringArr[i + c].length();
            int osId = Integer.parseInt(osStringArr[i].substring(1, n - 1).split(" ")[1]);
            c++;
            String description = osStringArr[i + c].split("=")[1];
            c++;
            List<String> vendorList = new ArrayList<>();
            if (osStringArr[i + c].contains("vendor_id")) {
                String vendorsString = osStringArr[i + c];
                c++;
                String[] vendorArr = vendorsString.split("-");
                for (int j = 1; j < vendorArr.length - 1; j++) {
                    vendorList.add(vendorArr[j]);
                }
            }
            List<String> printersList = new ArrayList<>();
            if (osStringArr[i + c].contains("fingerprints")) {
                String printers = osStringArr[i + c];
                c++;
                String[] printersArr = printers.split("-");
                for (int j = 1; j < printersArr.length; j++) {
                    List<Integer> integerRow = new ArrayList<>();
                    String[] row = printersArr[j].split(",");
                    for (String e : row) {
                        integerRow.add(Integer.valueOf(e.trim()));
                    }
                    String hexString = convertToHexString(integerRow);
                    printersList.add(hexString);
                }
            }
            if(osStringArr[i + c].contains("EOT")) {
                c++;
            }
            MyOs osObj = new MyOs(osId, description, vendorList, printersList);
            myOses.add(osObj);
            i += c;
        }
        List<Results> resultList = new ArrayList<>();
        for (MyClass myClass : myClasses) {
            Results r = new Results(myClass.getDescription(), null);
            List<MyOs> myOsesInResult = new ArrayList<>();

            for (MyOs myOs : myOses) {
                if (myOs.getOsId() >= myClass.getLowerBound()
                        && myOs.getOsId() <= myClass.getUpperBound()) {
                    myOsesInResult.add(myOs);
                }
            }
            r.setOs(myOsesInResult);
            resultList.add(r);
        }
        return resultList;
    }

    public String readOsFile(String filePath) {
        try {
            String results = "";
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isStartRead = false;
                boolean isVendor = false;
                boolean isPrinters = false;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) {
                        continue;
                    }
                    if (line.startsWith("[os")) {
                        isStartRead = true;
                    }

                    if (line.startsWith("fingerprints")) {
                        if (isVendor) {
                            results += "\n";
                        }
                        isVendor = false;
                        isPrinters = true;
                    }

                    if (line.startsWith("EOT")) {
                        if (isPrinters) {
                            results += "\n";
                        }
                        isPrinters = false;
                    }

                    if (line.startsWith("vendor_id=<<EOT")) {
                        isVendor = true;
                    }

                    if (!line.isEmpty() && isStartRead) {
                        if (isVendor || isPrinters) {
                            results += line + "-";
                        } else {
                            results += line + "\n";
                        }
                    }

                }
            }
            return results;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String convertToHexString(List<Integer> numbers) {
        String s = "";
        for (Integer i : numbers) {
            s += Integer.toHexString(i).length() == 1 ? "0" + Integer.toHexString(i)
                    : Integer.toHexString(i);
        }
        return s;
    }

    public String readClassFile(String filePath) {
        try {
            String results = "";
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) {
                        continue;
                    }
                    if (line.startsWith("[os")) {
                        return results;
                    }
                    if (!line.isEmpty()) {
                        results += line + "\n";
                    }
                }
            }
            return results;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
