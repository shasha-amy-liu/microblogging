package microblogging.vo;

import microblogging.model.Blog;

public class BlogVO {

    private String content;
    private String timeStamp;
    private String blogger;
    private String userId;
    private String id;

    public BlogVO(Blog blog) {
        setBlogger(blog.getUser().getUsername());
        setContent(blog.getContent());
        setId(blog.getId());
        String timeStamp = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(blog.getCreationDate());
        setTimeStamp(timeStamp);
        setUserId(blog.getUser().getId());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBlogger() {
        return blogger;
    }

    public void setBlogger(String blogger) {
        this.blogger = blogger;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
