package microblogging.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import microblogging.config.SpringMongodbConfig;
import microblogging.model.User;

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
        User u = new User("test" + UUID.randomUUID().toString(), UUID.randomUUID().toString());
        assertNotNull(userRepo.save(u));

        u = new User("test" + UUID.randomUUID().toString(), UUID.randomUUID().toString());
        assertNotNull(userRepo.save(u));
    }

    @Test
    public void testFindUserByUserName() {
        String u1Name = "test" + UUID.randomUUID().toString();
        User u1 = new User(u1Name, UUID.randomUUID().toString());
        assertNotNull(userRepo.save(u1));

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
