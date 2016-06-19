package microblogging.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import microblogging.config.SpringMongodbConfig;
import microblogging.model.Follow;
import microblogging.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongodbConfig.class})
public class FollowRepositoryTest {

    @Autowired
    private FollowRepository followRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    public void testGetAllFollower() {
        User u0 = new User("test" + UUID.randomUUID().toString(), UUID.randomUUID().toString());
        assertNotNull(userRepo.save(u0));

        User u1 = new User("test" + UUID.randomUUID().toString(), UUID.randomUUID().toString());
        userRepo.save(u1);

        User u2 = new User("test" + UUID.randomUUID().toString(), UUID.randomUUID().toString());
        userRepo.save(u2);

        Follow f = new Follow();
        f.setBloggerId(u0.getId());
        f.setFollowerId(u1.getId());
        followRepo.save(f);

        f = new Follow();
        f.setBloggerId(u0.getId());
        f.setFollowerId(u2.getId());
        followRepo.save(f);

        f = new Follow();
        f.setBloggerId(u1.getId());
        f.setFollowerId(u0.getId());
        followRepo.save(f);

        List<Follow> followers = followRepo.findByBloggerId(u0.getId());
        Assert.assertEquals(2, followers.size());

        List<Follow> followings = followRepo.findByFollowerId(u2.getId());
        Assert.assertEquals(1, followings.size());
    }

    @AfterClass
    public static void clean() {
        MongoTemplate mongoTemplate;
        try {
            mongoTemplate = new SpringMongodbConfig().mongoTemplate();
            mongoTemplate.dropCollection(Follow.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
