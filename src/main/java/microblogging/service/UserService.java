package microblogging.service;

import java.util.List;
import java.util.Set;

import microblogging.model.Blog;
import microblogging.model.User;

public interface UserService {

    void publishBlog(User a, Blog b);

    void followUser(User follower, User blogger);

    User update(User u);

    User save(User u);

    List<User> findUsersByUsername(String username);

    User findUserByUsername(String username);

    Set<User> listAllUsersNotFollowedYet(String userId);

    List<User> listIsFollowing(String userId);
}
