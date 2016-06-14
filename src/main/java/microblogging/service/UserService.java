package microblogging.service;

import java.util.List;
import java.util.Set;

import microblogging.model.Blog;
import microblogging.model.User;

public interface UserService {

    void publishBlog(User a, Blog b);

    void followUser(User a, User b);

    boolean update(User u);

    boolean save(User u);

    User getUserByName(String username);

    Set<User> listAllUsersNotFollowedYet(String username);

    List<Blog> getBlogTracking(String name);

    List<User> listAmFollowing(String username);

}
