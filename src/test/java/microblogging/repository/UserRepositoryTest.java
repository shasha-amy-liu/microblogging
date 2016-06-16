package microblogging.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import microblogging.config.SpringMongodbConfig;
import microblogging.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
// specify the configuration
@ContextConfiguration(classes = {SpringMongodbConfig.class})
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void test() {
        System.out.println("hello");
        List<User> users = repository.findAll();
        System.out.println(users.size());
        System.out.println(users.get(0));
    }

}
