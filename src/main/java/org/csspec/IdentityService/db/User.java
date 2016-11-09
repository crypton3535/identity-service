package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class User {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getContact() {
        return contact;
    }

    public static enum userType {
        TEACHER, STUDENT
    }

    ;

    @Id
    @JsonIgnore
    private String UUId;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @JsonProperty("contact")
    private String contact;

    @JsonProperty("address")
    private Address address = new Address();

    @JsonProperty("parent")
    private Parent parent = new Parent();

    @JsonProperty("isStudent")
    private int isStudent;

    public User(String userName, String email, String contact) {
        this.userName = userName;
        this.email = email;
        this.contact = contact;
    }

    public User() {
    }

    ;

    public String toString() {
        return "[ Userid : " + userId + ", Username : " + userName + ", Email : " + email + ", Isstudent : " + isStudent + " ] ";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(Address address) {
        this.address.setPermanentAddress(address.getPermanentAddress());
        this.address.setCurrentAddress(address.getCurrentAddress());
        this.address.setPostalAddress(address.getPostalAddress());
    }

    public void setParent(Parent parent) {
        this.parent.setFatherName(parent.getFatherName());
        this.parent.setMotherName(parent.getMotherName());
    }

    public void setIsStudent(int value) {
        this.isStudent = value;
    }
}
