package zuularch.department.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import zuularch.department.service.entity.Department;
import zuularch.department.service.repository.DepartmentRepostiory;
import zuularch.department.service.services.DepartmentService;

@Slf4j
@Component
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepostiory departmentRepostiory;

	@Override
	public Department persistDepartment(Department department) {
		return departmentRepostiory.save(department);
	}

	@Override
	public Department retrieveDepartmentById(String id) {
		Department department = departmentRepostiory.findDepartmentById(id);
		log.info(":::::Department {}", department);
		return department;

	}

}
