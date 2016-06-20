package microblogging.service;

import java.util.List;

import microblogging.model.Blog;
import microblogging.model.User;

public interface BlogService {

    Blog save(String blogContent, String username);
}
