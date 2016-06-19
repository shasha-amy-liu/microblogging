package microblogging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import microblogging.model.Follow;
import microblogging.repository.FollowRepository;
import microblogging.service.FollowerService;

public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowRepository followRepo;

    @Override
    public Follow save(Follow f) {
        return followRepo.save(f);
    }

    @Override
    public void remove(Follow f) {
        followRepo.delete(f);
    }

    @Override
    public List<Follow> getFollowers(String userId) {
        return followRepo.findByBloggerId(userId);
    }

    @Override
    public List<Follow> getFollowings(String followerId) {
        return followRepo.findByFollowerId(followerId);
    }
}
