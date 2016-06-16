package microblogging.dao;

import java.util.List;

import microblogging.model.Blog;
import microblogging.model.User;

public interface UserDAO {

    void publishBlog(User a, Blog blog);

    boolean update(User u);

    boolean save(User u);

    User getUserByName(String username);

    User getUserById(String id);

    List<String> getAllUserIds();

}
