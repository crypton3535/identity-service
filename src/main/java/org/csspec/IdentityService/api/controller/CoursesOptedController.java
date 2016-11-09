package org.csspec.IdentityService.api.controller;

import org.csspec.IdentityService.api.repo.CoursesOptedRepository;
import org.csspec.IdentityService.db.Course;
import org.csspec.IdentityService.db.CoursesOpted;
import org.csspec.IdentityService.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/academic")
public class CoursesOptedController {
    @Autowired
    CoursesOptedRepository coursesOptedRepository;

    @RequestMapping(value = "/mapping/all", method = RequestMethod.GET)
    public List<CoursesOpted> getAllMapping() {
        return coursesOptedRepository.findAll();
    }

    // stores the courses opted by a student , one at a time.
    @RequestMapping(value = "/users/student/course", method = RequestMethod.POST)
    public ResponseEntity<?> storeCourses(@RequestBody CoursesOpted coursesOpted) {
        if (coursesOpted.getCourseId() == null || coursesOpted.getStudentId() == null || coursesOpted.getTeacherId() == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        coursesOptedRepository.save(coursesOpted);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    //returns the string array of all courses opted by a student.
    @RequestMapping(value = "/courses/students/{userId}", method = RequestMethod.GET)
    public List<Map<String, String>> getAllCourses(@PathVariable String userId) {
        return coursesOptedRepository.findAllCourses(userId);
    }

    @RequestMapping(value = "/courses/faculty/{facultyId}", method = RequestMethod.GET)
    public List<Course> getCoursesOfFacultyByFacultyId(@PathVariable String facultyId) {
        return coursesOptedRepository.findCoursesOfFaculty(facultyId);
    }

    /*Find teachers teaching a particular course */
    @RequestMapping(value = "/teachers/course/{courseId}", method = RequestMethod.GET)
    public List<User> getTeachersOfCourse(@PathVariable String courseId) {
        return coursesOptedRepository.findTeachersOfCourse(courseId);
    }
}
