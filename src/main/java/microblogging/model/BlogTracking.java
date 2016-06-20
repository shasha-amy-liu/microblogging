package microblogging.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="microblogging_blogTrackings")
public class BlogTracking {

    @Id
    private String id;

    private String followerId;
    private String bloggerId;
    private String blogId;

    @PersistenceConstructor
    public BlogTracking(String followerId, String bloggerId, String blogId) {
        this.followerId = followerId;
        this.bloggerId = bloggerId;
        this.blogId = blogId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(String bloggerId) {
        this.bloggerId = bloggerId;
    }
}
