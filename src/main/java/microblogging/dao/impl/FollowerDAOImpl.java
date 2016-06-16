package microblogging.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import microblogging.dao.FollowerDAO;
import microblogging.model.Follower;

@Repository("followerDAO")
@Transactional
public class FollowerDAOImpl implements FollowerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public boolean add(Follower f) {
        System.out.println("follower saved");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(f);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Transactional
    @Override
    public boolean remove(Follower f) {
        System.out.println("follower removed");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(f);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Transactional
    @Override
    public List<String> getAllFollower(String userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> result = session.createQuery("select follower from  Follower where user = :id").setString("id", userId)
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Transactional
    @Override
    public List<String> getAllFollowing(String userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> result = session.createQuery("select user from  Follower where follower = :id").setString("id", userId)
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
