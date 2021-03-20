package zuularch.department.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import zuularch.department.service.entity.Department;
import zuularch.department.service.services.DepartmentService;

@Slf4j
@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @HystrixCommand
    @PostMapping("/persist")
    public ResponseEntity<ModelMap> persistDepartment(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ModelMap().addAttribute("dept", departmentService.persistDepartment(department)));
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ModelMap> retrieveDepartmentById(
        @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ModelMap().addAttribute("dept", departmentService.retrieveDepartmentById(id)));
    }
}
