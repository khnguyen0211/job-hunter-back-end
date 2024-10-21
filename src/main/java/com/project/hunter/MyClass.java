package com.project.hunter;

public class MyClass {
    private String classId;
    private String description;
    private int lowerBound;
    private int upperBound;

    public MyClass(String classId, String description, int lowerBound, int upperBound) {
        this.classId = classId;
        this.description = description;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyClass{");
        sb.append("classId=").append(classId);
        sb.append(", description=").append(description);
        sb.append('}');
        return sb.toString();
    }

}
