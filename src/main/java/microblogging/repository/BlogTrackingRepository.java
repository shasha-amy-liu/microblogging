package microblogging.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import microblogging.model.BlogTracking;

/**
 * Extend the standard interface
 */
@Repository
public interface BlogTrackingRepository extends MongoRepository<BlogTracking, String> {
}
