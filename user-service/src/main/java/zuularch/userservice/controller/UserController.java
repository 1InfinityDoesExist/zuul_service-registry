
package zuularch.userservice.controller;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import zuularch.userservice.entity.User;
import zuularch.userservice.services.UserService;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/persist")
    public ResponseEntity<?> persistUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ModelMap().addAttribute("user", userService.persistUser(user)));
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<?> retrieveUserById(
        @PathVariable(value = "id", required = true) String id) throws IOException, ParseException {
        ModelMap modelMap = new ModelMap();
        User user = userService.retrieveUserById(id);
        modelMap.put("user", user);
        log.info(":::::User {}", user);
        String url = "http://department-service/department/retrieve/" + user.getDepartmentId();
        String finalData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(finalData);
        for (Object obj : jsonObject.keySet()) {
            String param = (String) obj;
            if (param.equalsIgnoreCase("dept")) {
                modelMap.put("department", jsonObject.get(param));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelMap);

    }
}
