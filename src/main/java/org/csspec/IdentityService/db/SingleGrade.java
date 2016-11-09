package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleGrade {

    @JsonProperty("courseId")
    private String courseId;

    @JsonProperty("semesterId")
    private String semesterId;

    @JsonProperty("gradeScored")
    private int gradeScored;

    public SingleGrade() {
    }

    ;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public int getGradeScored() {
        return gradeScored;
    }

    public void setGradeScored(int gradeScored) {
        this.gradeScored = gradeScored;
    }
}
