package microblogging.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import microblogging.dao.BlogTrackingDAO;
import microblogging.model.BlogTracking;

@Repository("blogTrackingDAO")
@Transactional
public class BlogTrackingDAOImpl implements BlogTrackingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public boolean addTracking(String followerId, String bloggerId, String blogId) {
        System.out.println("blog tracking saved");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        BlogTracking tracking = new BlogTracking();
        tracking.setBloggerId(bloggerId);
        tracking.setFollowerId(followerId);
        tracking.setBlogId(blogId);

        session.save(tracking);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Transactional
    @Override
    public boolean removeTracking(String followerId, String bloggerId) {
        System.out.println("remove all blog tracking for one specific blogger and follower");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<BlogTracking> list = session.createQuery("from BlogTracking where followerId = :fid and bloggerId = :bid")
                .setString("fid", followerId).setString("bid", bloggerId).list();
        for (BlogTracking t : list) {
            session.delete(t);
        }
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Transactional
    @Override
    public List<BlogTracking> getTrackingByUser(String followerId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<BlogTracking> list = session.createQuery("from BlogTracking where followerId = :fid")
                .setString("fid", followerId).list();

        session.getTransaction().commit();
        session.close();
        return list;
    }

}
