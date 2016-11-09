package org.csspec.IdentityService.api.controller;

import org.csspec.IdentityService.api.repo.DepartmentRepository;
import org.csspec.IdentityService.db.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/academic")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.GET)
    public Department findDepartmentByDepartmentId(@PathVariable String departmentId) {
        return departmentRepository.findDepartmentByDepartmentId(departmentId);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public ResponseEntity<?> storeOneDepartment(@RequestBody Department department) {
        if (department.getDepartmentId() == null || department.getHODUserId() == null || department.getDepartmentName() == null) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        departmentRepository.save(department);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public List<Department> listAllDepartments() {
        return departmentRepository.findAll();
    }

}
