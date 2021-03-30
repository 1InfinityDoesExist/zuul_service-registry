package zuularch.department.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import zuularch.department.service.entity.Department;
import zuularch.department.service.services.DepartmentService;

@Slf4j
@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/persist")
    public ResponseEntity<ModelMap> persistDepartment(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ModelMap().addAttribute("dept", departmentService.persistDepartment(department)));
    }

    
    @RateLimiter(name = "deptService", fallbackMethod = "rateLimiter")
    @Retry(name = "retryDeptService", fallbackMethod = "retryFallBack")
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ModelMap> retrieveDepartmentById(
        @PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ModelMap().addAttribute("dept", departmentService.retrieveDepartmentById(id)));
    }

    public ResponseEntity<?> rateLimiter(String id, Throwable t) {
        log.info("----In Department Controler Class--Inside fall back method of rateLimiter-----");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Try karo after some time ", "12s");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).headers(responseHeaders)
            .body(new ModelMap().addAttribute("msg", t.getMessage()));
    }

    public ResponseEntity<?> retryFallBack(String id, Throwable t) {
        log.info("---Departement Service--Retry ----Retry ----Retry -----");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ModelMap()
            .addAttribute("error_msg", " DS Retry Failed....!!!!. Exception is " + t.toString()));
    }
}
