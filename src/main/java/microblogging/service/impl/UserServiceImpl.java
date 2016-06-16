package microblogging.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import microblogging.dao.BlogDAO;
import microblogging.dao.BlogTrackingDAO;
import microblogging.dao.FollowerDAO;
import microblogging.dao.UserDAO;
import microblogging.model.Blog;
import microblogging.model.BlogTracking;
import microblogging.model.Follower;
import microblogging.model.User;
import microblogging.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private FollowerDAO followerDAO;

    public FollowerDAO getFollowerDAO() {
        return followerDAO;
    }

    public void setFollowerDAO(FollowerDAO followerDAO) {
        this.followerDAO = followerDAO;
    }

    @Autowired
    private BlogDAO blogDAO;

    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @Autowired
    private BlogTrackingDAO blogTrackingDAO;

    public BlogTrackingDAO getBlogTrackingDAO() {
        return blogTrackingDAO;
    }

    public void setBlogTrackingDAO(BlogTrackingDAO blogTrackingDAO) {
        this.blogTrackingDAO = blogTrackingDAO;
    }

    @Autowired
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean save(User u) {
        return userDAO.save(u);
    }

    @Override
    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }

    @Override
    public boolean update(User u) {
        return userDAO.update(u);
    }

    @Override
    public void publishBlog(User a, Blog blog) {
        // get all followers
        List<String> followersId = followerDAO.getAllFollower(a.getId());
        for (String id : followersId) {
            blogTrackingDAO.addTracking(id, a.getId(), blog.getId());
        }
    }

    @Override
    public void followUser(User a, User b) {

        Follower f = new Follower();
        f.setUser(b.getId());
        f.setFollower(a.getId());
        followerDAO.add(f);

        List<Blog> blogs = blogDAO.getAllBlogsByUser(b);
        for (Blog blog : blogs) {
            blogTrackingDAO.addTracking(a.getId(), b.getId(), blog.getId());
        }
    }

    @Override
    public Set<User> listAllUsersNotFollowedYet(String username) {
        User u = getUserByName(username);
        System.out.println("user id = " + u.getId());
        List<String> followed = followerDAO.getAllFollower(u.getId());
        System.out.println("followed = " + Arrays.toString(followed.toArray()));
        List<String> allIds = userDAO.getAllUserIds();
        System.out.println("all ids = " + Arrays.toString(allIds.toArray()));
        allIds.removeAll(followed);
        allIds.remove(u.getId());

        System.out.println(" not followed = " + Arrays.toString(allIds.toArray()));
        Set<User> result = new HashSet<User>();
        for (String id : allIds) {
            result.add(userDAO.getUserById(id));
        }
        return result;
    }

    @Override
    public List<Blog> getBlogTracking(String name) {
        User u = getUserByName(name);

        String followerId = u.getId();
        List<BlogTracking> trackings = blogTrackingDAO.getTrackingByUser(followerId);
        List<Blog> result = new ArrayList<Blog>();
        for (BlogTracking track : trackings) {
            result.add(blogDAO.getBlogById(track.getBlogId()));
        }
        return result;
    }

    @Override
    public List<User> listAmFollowing(String username) {
        User u = getUserByName(username);

        String id = u.getId();

        List<String> amFollowingIds = followerDAO.getAllFollowing(id);
        List<User> users = new ArrayList<User>();

        for (String fid : amFollowingIds) {
            users.add(userDAO.getUserById(fid));
        }

        return users;
    }
}
