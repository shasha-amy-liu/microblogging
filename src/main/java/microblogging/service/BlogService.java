package microblogging.service;

import java.util.List;

import microblogging.model.Blog;

public interface BlogService {

    Blog save(String blogContent, String username);

    List<Blog> findByUserId(String userId);

    List<Blog> findBlogTrackingsByBloggerIdAndFollowerId(String bloggerId, String followerId);
}
