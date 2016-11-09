package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Student {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("studentId")
    private String studentId;

    @JsonProperty("currentCGPA")
    private double currentCGPA;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("departmentId")
    private String departmentId;

    @JsonProperty("yearOfJoining")
    private int yearOfJoining;

    @JsonProperty("grades")
    private List<SingleGrade> grades = new ArrayList<SingleGrade>();

    public Student() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getCurrentGPA() {
        return this.currentCGPA;
    }

    public void setCurrentGPA(double currentGPA) {
        this.currentCGPA = currentGPA;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public void setYearOfJoining(int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public List<SingleGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<SingleGrade> grades) {
        this.grades = grades;
    }
}
