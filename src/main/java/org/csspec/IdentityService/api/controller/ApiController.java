package org.csspec.IdentityService.api.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.csspec.IdentityService.config.RequestValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ApiController {
    @RequestMapping(value = "/identity/api", method = RequestMethod.GET)
    public List<Map<String, String>> getAllIdentityApi(HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "STUDENT", "TEACHER");
        List<Map<String, String>> answer = new ArrayList<>();
        String apimethods[] = {
                "GET /identity/users",
                "POST /identity/users",
                "GET /identity/users/{userId}",
                "GET /identity/users/students",
                "GET /identity/users/faculty",
                "GET /identity/users/student/{userId}",
                "GET /identity/users/teacher/{teacherId}",
                "GET /identity/users/student/courses/{courseId}",
                "GET /identity/users/student/{userId}/courses/{courseId}"
        };
        String description[] = {
                "Returns the list of all registered users",
                "Register a new user",
                "Returns the information about a particular user with given userId",
                "Returns the public information about all registered students",
                "Returns the public information about all registered faculty",
                "Returns a map containing public information about a user + information about a student",
                "Returns a map containing public information about a user + information about a faculty",
                "Returns array of userIds of all students who have registered for a course.",
                "Returns the information about the teacher who teaches course of courseId to student with given userId",
        };
        for (int i = 0; i < apimethods.length; i++) {
            Map<String, String> temp = new HashMap<>();
            temp.put("call", apimethods[i]);
            temp.put("description", description[i]);
            answer.add(temp);
        }
        return answer;
    }

    @RequestMapping(value = "/academic/api", method = RequestMethod.GET)
    List<Map<String, String>> getAllAcademicApi(HttpServletRequest request) {
        RequestValidator.checkHeader(request, "ADMIN", "STUDENT", "TEACHER");
        List<Map<String, String>> answer = new ArrayList<>();
        String apimethods[] = {
                "GET /academic/courses",
                "POST /academic/courses",
                "GET /academic/courses/{courseId}",
                "GET /academic/mapping/all",
                "GET /academic/users/student/course",
                "GET /academic/courses/students/{userId}",
                "GET /academic/courses/faculty/{facultyId}",
                "GET /academic/teachers/course/{courseId}",
                "POST /academic/departments",
                "GET /academic/departments",
                "GET /academic/departments/{departmentId}"
        };
        String description[] = {
                "Returns list of all courses",
                "Add a new course",
                "Returns information about a specific course.",
                "Returns complete student-teacher-course mapping",
                "Store a new mapping object",
                "List all courses opted by a student.",
                "List all courses taught by a teacher.",
                "List all teachers teaching a course",
                "Add a new department",
                "Lists all departments",
                "Returns information about a specific department"
        };

        for (int i = 0; i < apimethods.length; i++) {
            Map<String, String> temp = new HashMap<>();
            temp.put("call", apimethods[i]);
            temp.put("description", description[i]);
            answer.add(temp);
        }
        return answer;
    }

}
