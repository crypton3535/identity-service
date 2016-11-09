package org.csspec.IdentityService.api.repo;

import org.csspec.IdentityService.db.Course;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CourseRepository extends MongoRepository<Course, String> {
    public Course findCourseByCourseId(String courseId);
}
