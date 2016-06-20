package microblogging.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import microblogging.config.SpringMongodbConfig;
import microblogging.model.User;
import microblogging.util.MicrobloggingUtil;

@RunWith(SpringJUnit4ClassRunner.class)
// specify the configuration
@ContextConfiguration(classes = {SpringMongodbConfig.class})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepo;

    @Test
    public void testFindAll() {
        System.out.println("hello");
        List<User> users = userRepo.findAll();
        System.out.println(users.size());
        System.out.println(users.get(0));
    }

    @Test
    public void testSave() {
        User u = MicrobloggingUtil.generateRandomUser();
        assertNotNull(userRepo.save(u));

        u = MicrobloggingUtil.generateRandomUser();
        assertNotNull(userRepo.save(u));
    }

    @Test
    public void testFindUserByUserName() {
        User u1 = MicrobloggingUtil.generateRandomUser();
        assertNotNull(userRepo.save(u1));

        String u1Name = u1.getUsername();
        List<User> u = userRepo.findByUsername(u1Name);
        assertEquals(1, u.size());
    }

    @Test
    public void testGetAllIds() {
        List<User> users = userRepo.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindOneByUsername() {
        User user0 = userRepo.findAll().get(0);
        User u0 = userRepo.findOneByUsername(user0.getUsername());
        assertEquals(user0.getId(), u0.getId());
    }

    @Test
    public void testFindOneById() {
        User user0 = userRepo.findAll().get(0);
        User u0 = userRepo.findOneById(user0.getId());
        assertEquals(user0.getUsername(), u0.getUsername());
    }

    @AfterClass
    public static void clean() {
        MongoTemplate mongoTemplate;
        try {
            mongoTemplate = new SpringMongodbConfig().mongoTemplate();
            mongoTemplate.dropCollection(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
