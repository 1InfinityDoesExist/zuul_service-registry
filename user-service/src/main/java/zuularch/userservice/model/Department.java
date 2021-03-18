package zuularch.userservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
    private String id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
