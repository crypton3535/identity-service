package org.csspec.IdentityService.api.repo;


import org.csspec.IdentityService.db.Faculty;
import org.csspec.IdentityService.db.Student;
import org.csspec.IdentityService.db.User;
import javafx.util.Pair;
import org.springframework.http.ResponseEntity;


public interface UserOperations {
    Pair<User,Student> findSpecificStudent(String userId);
    Pair<User,Faculty> findSpecificFaculty(String userId);
    String[] findStudentsOfCourse(String courseId);
    ResponseEntity<?> findSpecificTeacherSpecial(String userId, String courseId);

   }
