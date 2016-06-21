package microblogging.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microblogging.model.Blog;
import microblogging.model.BlogTracking;
import microblogging.model.Follow;
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
    public List<User> findUsersByUsername(String username) {
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
    public void followUser(User follower, User blogger) {

        Follow f = new Follow();
        f.setFollowerId(follower.getId());
        f.setBloggerId(blogger.getId());
        followRepo.save(f);

        List<Blog> blogs = blogRepo.findByUserId(blogger.getId());
        for (Blog blog : blogs) {
            BlogTracking bt = new BlogTracking(follower.getId(), blogger.getId(), blog.getId());
            blogTrackingRepo.save(bt);
        }
    }

    @Override
    public Set<User> listAllUsersNotFollowedYet(String username) {
        User u = userRepo.findOneByUsername(username);
        System.out.println("user id = " + u.getId());
        List<Follow> followed = followRepo.findByBloggerId(u.getId());
        List<String> followerIds = new ArrayList<>();
        for (Follow f : followed) {
            followerIds.add(f.getFollowerId());
        }
        System.out.println("followed = " + Arrays.toString(followed.toArray()));
        List<User> allUsers = userRepo.findAll();
        List<String> allIds = new ArrayList<>();
        for (User user : allUsers) {
            allIds.add(user.getId());
        }
        System.out.println("all ids = " + Arrays.toString(allIds.toArray()));
        allIds.removeAll(followerIds);
        allUsers.remove(u);

        System.out.println(" not followed = " + Arrays.toString(allIds.toArray()));
        Set<User> result = new HashSet<User>();
        for (String id : allIds) {
            result.add(userRepo.findOneById(id));
        }
        return result;
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

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findOneByUsername(username);
    }
}
