package zuularch.department.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import zuularch.department.service.entity.Department;

@Repository
public interface DepartmentRepostiory extends MongoRepository<Department, String> {

	public Department findDepartmentById(String id);

}
