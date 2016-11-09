package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Address {
    @JsonProperty("permanentAddress")
    private String permanentAddress;

    @JsonProperty("currentAddress")
    private String currentAddress;

    @JsonProperty("postalAddress")
    private String postalAddress;

    Address() {
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentAddress() {
        return this.permanentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentAddress() {
        return this.currentAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostalAddress() {
        return this.postalAddress;
    }
}
