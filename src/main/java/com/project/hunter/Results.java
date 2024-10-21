package com.project.hunter;

import java.util.List;

public class Results {
    private String description;
    private List<MyOs> os;

    public Results(String description, List<MyOs> os) {
        this.description = description;
        this.os = os;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MyOs> getOs() {
        return os;
    }

    public void setOs(List<MyOs> os) {
        this.os = os;
    }
}

