package microblogging.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import microblogging.model.User;

/**
 * Extend both the standard
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUsername(String username);

    User findOneByUsername(String username);

    User findOneById(String userId);

    // Not, where x.id <> ?1
    List<User> findByIdNot(String userId);
}
