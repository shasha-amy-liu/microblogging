package microblogging.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microblogging.model.Blog;
import microblogging.model.BlogTracking;
import microblogging.model.Follow;
import microblogging.model.User;
import microblogging.repository.BlogRepository;
import microblogging.repository.BlogTrackingRepository;
import microblogging.repository.FollowRepository;
import microblogging.repository.UserRepository;
import microblogging.service.BlogService;

// service annotation is applied to class implementation, not the interface
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BlogTrackingRepository blogTrackingRepo;

    @Autowired
    private FollowRepository followRepo;

    @Autowired
    private BlogRepository blogRepo;

    @Override
    public Blog save(String blogContent, String userId) {
        User blogger = userRepo.findOneById(userId);

        Blog blog = new Blog(blogger.getId(), blogContent);
        blogRepo.save(blog);

        // Publish to blogTracking
        List<Follow> follows = followRepo.findByBloggerId(blogger.getId());
        for (Follow f : follows) {
            BlogTracking bt = new BlogTracking(f.getFollowerId(), f.getBloggerId(), blog.getId());
            blogTrackingRepo.save(bt);
        }
        return blog;
    }

    @Override
    public List<Blog> findByUserId(String userId) {
        return blogRepo.findByUserId(userId);
    }

    @Override
    public List<Blog> findBlogTrackingsByBloggerIdAndFollowerId(String bloggerId, String followerId) {
        List<Blog> result = new ArrayList<>();
        List<BlogTracking> blogTrackings = blogTrackingRepo.findByFollowerIdAndBloggerId(followerId, bloggerId);
        for (BlogTracking bt : blogTrackings) {
            result.add(blogRepo.findById(bt.getBlogId()));
        }
        return result;
    }
}
