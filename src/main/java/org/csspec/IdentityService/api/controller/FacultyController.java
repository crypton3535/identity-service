package org.csspec.IdentityService.api.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.csspec.IdentityService.api.repo.FacultyRepository;
import org.csspec.IdentityService.config.RequestValidator;
import org.csspec.IdentityService.db.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacultyController {
    @Autowired
    public FacultyRepository facultyRepository;

    @RequestMapping(value = "/identity/faculty", method = RequestMethod.POST)
    public ResponseEntity<?> addOneFaculty(@RequestBody Faculty faculty, HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "FACULTY");
        if (faculty.getFacultyId() == null || faculty.getUserID() == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        facultyRepository.save(faculty);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
}
