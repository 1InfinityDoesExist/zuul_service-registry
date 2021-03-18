package zuularch.userservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zuularch.userservice.entity.User;
import zuularch.userservice.repository.UserRepository;
import zuularch.userservice.services.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User persistUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User retrieveUserById(String id) {
        return userRepository.findUserById(id);
    }

}
