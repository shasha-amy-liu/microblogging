package microblogging.vo;

import microblogging.model.User;

public class UserVO {

    private String username;
    private String id;

    public UserVO(User u) {
        this.username = u.getUsername();
        this.id = u.getId();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
