package microblogging.service;

import java.util.List;

import microblogging.model.Follow;

public interface FollowerService {

    Follow save(Follow f);

    void remove(Follow f);

    List<Follow> getFollowers(String bloggerId);

    List<Follow> getFollowings(String followerId);
}
