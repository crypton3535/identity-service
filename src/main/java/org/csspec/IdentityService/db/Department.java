package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Department {

    @JsonProperty("departmentId")
    private String departmentId;

    @JsonProperty("departmentName")
    private String departmentName;

    @JsonProperty("departmentDescription")
    private String departmentDescription;

    @JsonProperty("departmentAddress")
    private String departmentAddress;

    @JsonProperty("HODUserId")
    private String HODUserId;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHODUserId() {
        return HODUserId;
    }

    public void setHODUserId(String HODUserId) {
        this.HODUserId = HODUserId;
    }

    public String toString() {
        return " [ departmentId : " + departmentId + ", departmentName : " + departmentName + ", departmentDescription : " + departmentDescription + " ] ";
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }
}
