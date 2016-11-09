package org.csspec.IdentityService.api.controller;

import javafx.util.Pair;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.csspec.IdentityService.api.repo.StudentRepository;
import org.csspec.IdentityService.api.repo.UserRepository;
import org.csspec.IdentityService.config.RequestValidator;
import org.csspec.IdentityService.db.Faculty;
import org.csspec.IdentityService.db.Student;
import org.csspec.IdentityService.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/identity")
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    StudentRepository studentRepository;

    //returns public information about all the users.
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "TEACHER");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Request to list all users.");
        return new ResponseEntity<Object>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/students/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentId(@PathVariable String studentId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "FACULTY", "STUDENT");
        Student temp = studentRepository.findStudentByStudentId(studentId);
        if (temp == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Object>(temp.getUserId(), HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable String userId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN");
        java.util.Date date = new java.util.Date();
        User temp = repository.findUserByUserId(userId);
        if (temp == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Deleted a user - " + getOneUser(userId));
        repository.delete(repository.findOne(userId));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // registers a new user.
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> storeOneUser(@RequestBody User user, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN");
        java.util.Date date = new java.util.Date();
        System.out.println("[ " + new Timestamp(date.getTime()) + " ] Registered a new User : " + user);
        repository.save(user);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    public User getOneUser(String userId) {
        return repository.findUserByUserId(userId);
    }

    //returns information about a particular user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSpecificUser(@PathVariable String userId, HttpServletRequest request) {
        if (!RequestValidator.checkHeader(request, "ADMIN").getRole().equals("ADMIN") &&
                !Objects.equals(RequestValidator.checkHeader(request, "STUDENT", "TEACHER").getUserid(), userId)) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Get information about userId " + userId);
        return new ResponseEntity<Object>(repository.findUserByUserId(userId), HttpStatus.OK);
    }

    //returns list of all students.
    @RequestMapping(value = "/users/students", method = RequestMethod.GET)
    public List<User> getAllStudents(HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Request to list all students.");
        return repository.findAllStudents();
    }

    //returns list of all faculty members.
    @RequestMapping(value = "/users/faculty", method = RequestMethod.GET)
    public List<User> getAllFaculty(HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Request to list all faculty.");
        return repository.findAllFaculty();
    }

    //returns a pair key: has public information and value: has personal information about a particular faculty
    @RequestMapping(value = "/users/faculty/{userId}", method = RequestMethod.GET)
    public Pair<User, Faculty> getSpecificFaculty(@PathVariable String userId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "TEACHER");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Get information about a particular faculty " + userId);
        return repository.findSpecificFaculty(userId);
    }

    //returns a pair key: has public information and value: has personal information about a particular student.
    @RequestMapping(value = "/users/student/{userId}", method = RequestMethod.GET)
    public Pair<User, Student> getSpecificStudent(@PathVariable String userId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "TEACHER", "STUDENT");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Get information about a particular student " + userId);
        return repository.findSpecificStudent(userId);
    }

    //returns array of userIds of all students who have registered for a course.
    @RequestMapping(value = "/users/student/courses/{courseId}", method = RequestMethod.GET)
    public String[] getStudentsOfCourse(@PathVariable String courseId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "TEACHER", "STUDENT");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Request to find all students opted for " + courseId);
        return repository.findStudentsOfCourse(courseId);
    }

    //returns the information about the teacher who teaches course of courseId to student with given userId
    @RequestMapping(value = "/users/student/{userId}/courses/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSpecificTeacher(@PathVariable String userId, @PathVariable String courseId, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "TEACHER", "STUDENT");
        java.util.Date date = new java.util.Date();
        System.out.println(" [ " + new Timestamp(date.getTime()) + " ] Request to find teacher with course " + courseId + " and user " + userId);
        return repository.findSpecificTeacherSpecial(userId, courseId);
    }
}
