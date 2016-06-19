package microblogging.service;

import java.util.List;
import java.util.Set;

import microblogging.model.Blog;
import microblogging.model.User;

public interface UserService {

    void publishBlog(User a, Blog b);

    void followUser(User a, User b);

    User update(User u);

    User save(User u);

    List<User> findUserByUsername(String username);

    Set<User> listAllUsersNotFollowedYet(String username);

    List<Blog> getBlogTracking(String name);

    List<User> listAmFollowing(String username);

}
