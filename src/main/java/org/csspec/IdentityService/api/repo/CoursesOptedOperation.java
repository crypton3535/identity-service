package org.csspec.IdentityService.api.repo;

import org.csspec.IdentityService.db.Course;
import org.csspec.IdentityService.db.User;

import java.util.List;
import java.util.Map;

public interface CoursesOptedOperation {
    List<Map<String, String>> findAllCourses(String userId);

    List<Course> findCoursesOfFaculty(String facultyId);

    List<User> findTeachersOfCourse(String courseId);
}
