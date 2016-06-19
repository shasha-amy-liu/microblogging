package microblogging.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import microblogging.model.User;

/**
 * Extend the standard interface
 */
@Repository
public interface BlogRepository extends MongoRepository<User, String> {
}
