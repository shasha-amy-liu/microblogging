package microblogging.dao;

import java.util.List;

import microblogging.model.Blog;
import microblogging.model.User;

public interface BlogDAO {

    Blog save(String blogContent, String username);

    List<Blog> getAllBlogsByUser(User u);

    Blog getBlogById(Long blogId);

}
