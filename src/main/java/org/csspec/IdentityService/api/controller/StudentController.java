package org.csspec.IdentityService.api.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.csspec.IdentityService.api.repo.StudentRepository;
import org.csspec.IdentityService.config.RequestValidator;
import org.csspec.IdentityService.db.Student;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    public StudentRepository studentRepository;

    @RequestMapping(value = "/identity/student", method = RequestMethod.POST)
    public ResponseEntity<?> addOneStudent(@RequestBody Student student, HttpServletRequest request) {
        RequestValidator.checkHeader(request,"ADMIN","FACULTY");
        if(student.getUserId() == null || student.getStudentId()==null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        studentRepository.save(student);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

}
