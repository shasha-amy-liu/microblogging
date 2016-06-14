package microblogging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import microblogging.dao.BlogDAO;
import microblogging.dao.BlogTrackingDAO;
import microblogging.dao.FollowerDAO;
import microblogging.dao.UserDAO;
import microblogging.model.Blog;
import microblogging.model.User;
import microblogging.service.BlogService;

@Component("blogService")
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BlogTrackingDAO blogTrackingDAO;
	
	@Autowired
	private FollowerDAO followerDAO;
	
	@Autowired
	private BlogDAO blogDAO;
	
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	@Override
	public Blog save(String blogContent, String username) {
		User blogger = userDAO.getUserByName(username);
		
		Blog blog = blogDAO.save(blogContent, username);
		// publish to blogTracking
		List<Long> followers = followerDAO.getAllFollower(blogger.getId());
		for (Long followerId : followers) {
			blogTrackingDAO.addTracking(followerId, blogger.getId(), blog.getId());
		}
		return blog;		
	}

	@Override
	public List<Blog> getAllBlogsByUser(User u) {
		return blogDAO.getAllBlogsByUser(u);
	}

	@Override
	public Blog getBlogById(Long blogId) {
		return blogDAO.getBlogById(blogId);
	}
}
