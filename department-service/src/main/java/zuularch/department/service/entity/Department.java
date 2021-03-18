package zuularch.department.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "department")
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {

    @Id
    private String id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
