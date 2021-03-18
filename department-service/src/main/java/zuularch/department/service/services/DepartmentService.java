package zuularch.department.service.services;

import org.springframework.stereotype.Service;

import zuularch.department.service.entity.Department;

@Service
public interface DepartmentService {

	public Department persistDepartment(Department department);

	public Department retrieveDepartmentById(String id);

}
