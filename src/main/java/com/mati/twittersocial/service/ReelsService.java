package com.mati.twittersocial.service;

import com.mati.twittersocial.model.Reels;
import com.mati.twittersocial.model.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();
    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
