package zuularch.userservice.services;

import org.springframework.stereotype.Service;
import zuularch.userservice.entity.User;

@Service
public interface UserService {

    public User persistUser(User user);

    public User retrieveUserById(String id);

}
