package org.csspec.IdentityService.api.repo;

import org.csspec.IdentityService.db.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String>{
    public Student findStudentByStudentId(String studentId);
}
