package microblogging.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import microblogging.model.Follow;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String>{
}
