package microblogging.dao;

import java.util.List;

import microblogging.model.BlogTracking;

public interface BlogTrackingDAO {

	boolean addTracking(Long followerId, Long bloggerId, Long blogId);
	
	boolean removeTracking(Long followerId, Long bloggerId);

	List<BlogTracking> getTrackingByUser(Long followerId);	
}
