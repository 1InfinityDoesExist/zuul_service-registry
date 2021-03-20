package in.zuul.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping
@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        log.info(":::::Inside FallBackMethodController Class userFallback");
        return "User service is tatking long then expected.";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod() {
        log.info(":::::Inside FallBackMethodController Class departmentFallback");
        return "Department service is tatking long then expected.";
    }

}
