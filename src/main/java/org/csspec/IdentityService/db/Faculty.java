package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Faculty {

    @JsonProperty("userId")
    private String userID;

    @JsonProperty("facultyId")
    private String facultyId;

    @JsonProperty("departmentId")
    private int departmentId;

    @JsonProperty("areaOfResearch")
    private String areaOfResearch;

    public Faculty() {
    }

    ;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getAreaOfResearch() {
        return areaOfResearch;
    }

    public void setAreaOfResearch(String areaOfResearch) {
        this.areaOfResearch = areaOfResearch;
    }
}
