package org.csspec.IdentityService.api.repo;

import org.csspec.IdentityService.db.CoursesOpted;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CoursesOptedRepository extends MongoRepository<CoursesOpted, String>, CoursesOptedOperation {
    public CoursesOpted findCoursesOptedByCourseIdAndStudentId(String courseId, String studentId);
}
