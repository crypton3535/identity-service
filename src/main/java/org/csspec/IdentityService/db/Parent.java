package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
    @JsonProperty("fatherName")
    private String fatherName;

    @JsonProperty("motherName")
    private String motherName;

    Parent() {
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherName() {
        return this.motherName;
    }

}
