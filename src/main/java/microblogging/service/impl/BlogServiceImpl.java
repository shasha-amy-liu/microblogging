package microblogging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import microblogging.model.Blog;
import microblogging.model.BlogTracking;
import microblogging.model.Follow;
import microblogging.model.User;
import microblogging.repository.BlogRepository;
import microblogging.repository.BlogTrackingRepository;
import microblogging.repository.FollowRepository;
import microblogging.repository.UserRepository;
import microblogging.service.BlogService;

@Component("blogService")
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
    public Blog save(String blogContent, String username) {
        User blogger = userRepo.findOneByUsername(username);

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
}
