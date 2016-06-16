package microblogging.dao;

import java.util.List;

import microblogging.model.Follower;

public interface FollowerDAO {

    boolean add(Follower f);

    boolean remove(Follower f);

    List<Long> getAllFollower(Long userId);

    List<Long> getAllFollowing(Long userId);
}
