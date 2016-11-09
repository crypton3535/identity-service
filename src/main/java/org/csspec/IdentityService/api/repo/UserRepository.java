package org.csspec.IdentityService.api.repo;

import org.csspec.IdentityService.db.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String>, UserOperations {
    public User findUserByUserId(String userId);

    @Query("{'isStudent' : 1 }")
    public List<User> findAllStudents();

    @Query("{'isStudent' : 0 }")
    public List<User> findAllFaculty();

}
