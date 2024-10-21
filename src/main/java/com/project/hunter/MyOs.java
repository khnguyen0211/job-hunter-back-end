package com.project.hunter;

import java.util.List;

public class MyOs {
    private int osId;
    private String description;
    private List<String> vendors;
    private List<String> fingerprints;



    public MyOs(int osId, String description, List<String> vendors, List<String> fingerprints) {
        this.osId = osId;
        this.description = description;
        this.vendors = vendors;
        this.fingerprints = fingerprints;
    }



    public int getOsId() {
        return osId;
    }



    public void setOsId(int osId) {
        this.osId = osId;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public List<String> getVendors() {
        return vendors;
    }



    public void setVendors(List<String> vendors) {
        this.vendors = vendors;
    }



    public List<String> getFingerprints() {
        return fingerprints;
    }



    public void setFingerprints(List<String> fingerprints) {
        this.fingerprints = fingerprints;
    }



    @Override
    public String toString() {
        return "MyOs: \nosId=" + osId + "\ndescription=" + description + "\nvendors=" + vendors
                + "\nfingerprints=" + fingerprints;
    }



}
