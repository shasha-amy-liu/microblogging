package microblogging.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import microblogging.config.RootConfig;
import microblogging.config.SpringMongodbConfig;
import microblogging.model.Blog;
import microblogging.model.User;
import microblogging.service.BlogService;
import microblogging.service.UserService;
import microblogging.util.MicrobloggingUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongodbConfig.class, RootConfig.class})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @Test
    public void testAddAndFollow() {
        User u1 = MicrobloggingUtil.generateRandomUser();
        userService.save(u1);

        User u2 = MicrobloggingUtil.generateRandomUser();
        userService.save(u2);

        User u3 = MicrobloggingUtil.generateRandomUser();
        userService.save(u3);

        userService.followUser(u1, u2);
        userService.followUser(u1, u3);
    }

    @Test
    public void testPublish() {
        User u = MicrobloggingUtil.generateRandomUser();
        userService.save(u);

        String blogContent = "some random content";
        Blog uBlog = blogService.save(blogContent, u.getUsername());
        userService.publishBlog(u, uBlog);
    }

    @Test
    public void testAddFollowerAndPublish() {
        User u1 = MicrobloggingUtil.generateRandomUser();
        u1.setUsername("test" + UUID.randomUUID());
        u1.setPassword("pass2");
        userService.save(u1);
        System.out.println("u1 id = " + u1.getId());

        User u = MicrobloggingUtil.generateRandomUser();
        u.setUsername("test" + UUID.randomUUID());
        u.setPassword("pass3");
        userService.save(u);

        // u1 follows u, so every time u publish something, notify u1
        userService.followUser(u1, u);

        String blogContent = "some random content";
        Blog uBlog = blogService.save(blogContent, u.getUsername());
        userService.publishBlog(u, uBlog);

        String blogContent1 = "u publish some random content 1";
        Blog uBlog1 = blogService.save(blogContent1, u.getUsername());
        System.out.println("id1 = " + uBlog1.getId());

        userService.publishBlog(u, uBlog1);
    }

    @Test
    public void testlistAllUsersNotFollowedYet() {
        User u1 = MicrobloggingUtil.generateRandomUser();
        String u1Name = u1.getUsername();
        Assert.assertNotNull(userService.save(u1));
        System.out.println("u1 id = " + u1.getId());

        User u2 = MicrobloggingUtil.generateRandomUser();
        userService.save(u2);
        System.out.println("u2 id = " + u2.getId());

        User u3 = MicrobloggingUtil.generateRandomUser();
        userService.save(u3);
        System.out.println("u3 id = " + u3.getId());

        User u = MicrobloggingUtil.generateRandomUser();
        userService.save(u);

        u = MicrobloggingUtil.generateRandomUser();
        userService.save(u);

        userService.followUser(u2, u1);
        userService.followUser(u3, u1);

        Set<User> notFollowedYet = userService.listAllUsersNotFollowedYet(u1Name);
        for (User user : notFollowedYet) {
            System.out.println(user.getId());
        }
    }

    @Test
    public void testAddTrackingBlog() {
        User u1 = MicrobloggingUtil.generateRandomUser();
        Assert.assertNotNull(userService.save(u1));
        System.out.println("u1 id = " + u1.getId());

        User u2 = MicrobloggingUtil.generateRandomUser();
        userService.save(u2);

        // u2 publish blogs
        String blogContent = "some random content";

        Assert.assertNotNull(blogService.save(blogContent, u2.getUsername()));

        String blogContent2 = "some random content 2";
        Assert.assertNotNull(blogService.save(blogContent2, u2.getUsername()));

        System.out.println("u2 id = " + u2.getId());

        // u1 follows u2
        userService.followUser(u1, u2);

        // Now u1 should have a list of blogs published by u2
        List<Blog> tracking = blogService.findBlogTrackingsByBloggerIdAndFollowerId(u2.getId(), u1.getId());
        int oldCount = tracking.size();

        // u2 continue to publish blog
        String blogContent3 = "some random content 3";
        Assert.assertNotNull(blogService.save(blogContent3, u2.getUsername()));

        // should be old size +1
        tracking = blogService.findBlogTrackingsByBloggerIdAndFollowerId(u2.getId(), u1.getId());
        Assert.assertEquals(tracking.size(), oldCount + 1);
    }
}
