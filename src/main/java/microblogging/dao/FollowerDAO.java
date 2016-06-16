package microblogging.dao;

import java.util.List;

import microblogging.model.Follower;

public interface FollowerDAO {

    boolean add(Follower f);

    boolean remove(Follower f);

    List<String> getAllFollower(String userId);

    List<String> getAllFollowing(String userId);
}
