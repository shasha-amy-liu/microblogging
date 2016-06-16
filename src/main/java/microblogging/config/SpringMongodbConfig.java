package microblogging.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Mongodb configuration
 */
@Configuration
// must specify the places for repository
@EnableMongoRepositories("microblogging.repository")
public class SpringMongodbConfig extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "testdb";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost");
    }
}
