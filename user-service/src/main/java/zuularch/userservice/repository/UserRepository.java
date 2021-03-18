package zuularch.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import zuularch.userservice.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findUserById(String id);

}
