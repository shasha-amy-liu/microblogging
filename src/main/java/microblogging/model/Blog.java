package microblogging.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="microblogging_blogs")
public class Blog {

    private String id;
    private Date creationDate;
    private User user;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getBlogs().add(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
