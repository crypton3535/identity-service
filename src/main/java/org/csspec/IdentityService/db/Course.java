package org.csspec.IdentityService.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Course {

    @Id
    @JsonProperty("courseId")
    private String courseId;

    @JsonProperty("courseName")
    private String courseName;

    @JsonProperty("departmentId")
    private String departmentId;

    @JsonProperty("courseDescription")
    private String courseDescription;

    @JsonProperty("courseCredits")
    private int courseCredits;

    public Course() {
    }

    ;

    public String getCourseName() {
        return this.courseName;
    }

    public String toString() {
        String answer = "{CourseId : " + this.courseId +
                ",CourseName : " + this.courseName +
                ",DepartmentId : " + this.departmentId +
                ",CourseDescription : " + this.courseDescription;
        return answer;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }
}
