package microblogging.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microblogging.model.Blog;
import microblogging.model.User;
import microblogging.repository.BlogRepository;
import microblogging.repository.BlogTrackingRepository;
import microblogging.repository.FollowRepository;
import microblogging.repository.UserRepository;
import microblogging.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private FollowRepository followRepo;

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private BlogTrackingRepository blogTrackingRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public User save(User u) {
        return userRepo.save(u);
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User update(User u) {
        return userRepo.save(u);
    }

    @Override
    public void publishBlog(User a, Blog blog) {
//        // get all followers
//        List<String> followersId = followerDAO.getAllFollower(a.getId());
//        for (String id : followersId) {
//            blogTrackingDAO.addTracking(id, a.getId(), blog.getId());
//        }
    }

    @Override
    public void followUser(User a, User b) {

//        Follow f = new Follow();
//        f.setUser(b.getId());
//        f.setFollower(a.getId());
//        followerDAO.add(f);
//
//        List<Blog> blogs = blogDAO.getAllBlogsByUser(b);
//        for (Blog blog : blogs) {
//            blogTrackingDAO.addTracking(a.getId(), b.getId(), blog.getId());
//        }
    }

    @Override
    public Set<User> listAllUsersNotFollowedYet(String username) {
        return null;
//        User u = getUserByName(username);
//        System.out.println("user id = " + u.getId());
//        List<String> followed = followerDAO.getAllFollower(u.getId());
//        System.out.println("followed = " + Arrays.toString(followed.toArray()));
//        List<String> allIds = userDAO.getAllUserIds();
//        System.out.println("all ids = " + Arrays.toString(allIds.toArray()));
//        allIds.removeAll(followed);
//        allIds.remove(u.getId());
//
//        System.out.println(" not followed = " + Arrays.toString(allIds.toArray()));
//        Set<User> result = new HashSet<User>();
//        for (String id : allIds) {
//            result.add(userDAO.getUserById(id));
//        }
//        return result;
    }

    @Override
    public List<Blog> getBlogTracking(String name) {
        return null;
//        User u = getUserByName(name);
//
//        String followerId = u.getId();
//        List<BlogTracking> trackings = blogTrackingDAO.getTrackingByUser(followerId);
//        List<Blog> result = new ArrayList<Blog>();
//        for (BlogTracking track : trackings) {
//            result.add(blogDAO.getBlogById(track.getBlogId()));
//        }
//        return result;
    }

    @Override
    public List<User> listAmFollowing(String username) {
        return null;
//        User u = getUserByName(username);
//
//        String id = u.getId();
//
//        List<String> amFollowingIds = followerDAO.getAllFollowing(id);
//        List<User> users = new ArrayList<User>();
//
//        for (String fid : amFollowingIds) {
//            users.add(userDAO.getUserById(fid));
//        }
//
//        return users;
    }
}
