package org.csspec.IdentityService.api.repo;


import org.csspec.IdentityService.db.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacultyRepository extends MongoRepository<Faculty, String> {
    public Faculty findFacultyByFacultyId(String facultyId);
}
