package microblogging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import microblogging.dao.FollowerDAO;
import microblogging.model.Follow;
import microblogging.service.FollowerService;

public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerDAO followerDAO;

    @Override
    public boolean add(Follow f) {
        return followerDAO.add(f);
    }

}
