package microblogging.dao;

import java.util.List;

import microblogging.model.BlogTracking;

public interface BlogTrackingDAO {

    boolean addTracking(String followerId, String bloggerId, String blogId);

    boolean removeTracking(String followerId, String bloggerId);

    List<BlogTracking> getTrackingByUser(String followerId);
}
