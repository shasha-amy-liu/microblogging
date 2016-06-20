package microblogging.service;

import java.util.List;

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
import microblogging.util.MicrobloggingUtil;

// Must specify all the configuration classes that are used in the test cases
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongodbConfig.class, RootConfig.class})
public class BlogServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Test
    public void testSave() {
        User u = MicrobloggingUtil.generateRandomUser();
        userService.save(u);

        String blogContent = "some random content";

        Assert.assertNotNull(blogService.save(blogContent, u.getUsername()));

        String blogContent2 = "some random content 2";
        Assert.assertNotNull(blogService.save(blogContent2, u.getUsername()));
    }

    @Test
    public void testGetAllBlogs() {
        User u = MicrobloggingUtil.generateRandomUser();
        userService.save(u);

        String blogContent = "some random content";

        Assert.assertNotNull(blogService.save(blogContent, u.getUsername()));

        String blogContent2 = "some random content 2";
        Assert.assertNotNull(blogService.save(blogContent2, u.getUsername()));

        List<Blog> result = blogService.findByUserId(u.getId());
        Assert.assertEquals(2, result.size());
        for (Blog blog : result) {
            System.out.println(blog);
        }
    }
}
