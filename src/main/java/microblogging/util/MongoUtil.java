package microblogging.util;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import microblogging.model.User;

/**
 * Refer to http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.core
 */
public class MongoUtil {

    private static final Logger log = LoggerFactory.getLogger(MongoUtil.class);

    public static void main(String[] args) {
        MongoOperations mongoOps = null;
        try {
            // new Mango() is depreciated now and use MongoClient instead
            mongoOps = new MongoTemplate(new MongoClient("localhost"), "testdb");
            mongoOps.insert(new User("john", "password"));
        } catch (UnknownHostException e) {
            log.error("unknown host", e);
        } finally {
            if (mongoOps != null) {
                mongoOps.dropCollection("microblogging_users");
            }
        }
    }
}
