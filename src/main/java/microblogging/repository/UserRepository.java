package microblogging.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import microblogging.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
}
