package org.csspec.IdentityService.api.repo;

import org.csspec.IdentityService.db.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    public Department findDepartmentByDepartmentId(String departmentId);
}
