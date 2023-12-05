package com.mati.twittersocial.service;

import com.mati.twittersocial.model.Reels;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImpl implements ReelsService {
    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;


    @Override
    public Reels createReel(Reels reel, User user) {

        Reels createdReel = new Reels();

        createdReel.setTitle(reel.getTitle());
        createdReel.setUser(user);
        createdReel.setVideo(reel.getVideo());

        return reelsRepository.save(createdReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
