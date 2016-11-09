package org.csspec.IdentityService.api.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.csspec.IdentityService.api.repo.CourseRepository;
import org.csspec.IdentityService.config.RequestValidator;
import org.csspec.IdentityService.db.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/academic")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    //returns the information about a course of given courseId
    @RequestMapping(value = "/courses/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCourseByCourseId(@PathVariable String courseId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "TEACHER", "STUDENT");
        Course temp = courseRepository.findCourseByCourseId(courseId);
        if (temp == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(temp, HttpStatus.OK);
    }

    //stores into database a new course
    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity<?> storeOneCourse(@RequestBody Course course, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN");
        if (course.getCourseId() == null || course.getCourseName() == null || course.getDepartmentId() == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        courseRepository.save(course);
        java.util.Date date = new java.util.Date();
        System.out.println("[ " + new Timestamp(date.getTime()) + " ] Inserted a new course into Database " + course);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    //returns the list of all courses of college.
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> getAllCourse(HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "STUDENT", "TEACHER");
        return courseRepository.findAll();
    }


}
