package microblogging.vo;

import org.springframework.beans.factory.annotation.Autowired;

import microblogging.model.Blog;
import microblogging.model.User;
import microblogging.repository.UserRepository;
import microblogging.util.MicrobloggingUtil;

public class BlogVO {

    private String content;
    private String creationDate;
    private String bloggerUsername;
    private String bloggerId;
    private String id;

    @Autowired
    private UserRepository userRepo;

    public BlogVO(Blog blog) {
        String bloggerId = blog.getUserId();
        setBloggerId(bloggerId);
        User blogger = userRepo.findOneById(bloggerId);
        setBloggerUsername(blogger.getUsername());
        setContent(blog.getContent());
        setId(blog.getId());
        setCreationDate(MicrobloggingUtil.formatDate(blog.getCreationDate()));
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(String bloggerId) {
        this.bloggerId = bloggerId;
    }

    public String getBloggerUsername() {
        return bloggerUsername;
    }

    public void setBloggerUsername(String bloggerUsername) {
        this.bloggerUsername = bloggerUsername;
    }
}
