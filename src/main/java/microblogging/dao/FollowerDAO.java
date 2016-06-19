package microblogging.dao;

import java.util.List;

import microblogging.model.Follow;

public interface FollowerDAO {

    boolean add(Follow f);

    boolean remove(Follow f);

    List<String> getAllFollower(String userId);

    List<String> getAllFollowing(String userId);
}
