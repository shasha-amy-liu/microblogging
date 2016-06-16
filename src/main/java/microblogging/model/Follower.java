package microblogging.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Follower {

    @Id
    private Long id;

    @DBRef
    private Long user;
    private Long follower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getFollower() {
        return follower;
    }

    public void setFollower(Long follower) {
        this.follower = follower;
    }
}
