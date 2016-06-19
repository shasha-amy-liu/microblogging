package microblogging.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="microblogging_follow")
public class Follow {

    @Id
    private String id;
    private String bloggerId;
    private String followerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(String bloggerId) {
        this.bloggerId = bloggerId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }
}
