package com.mati.twittersocial.controller;

import com.mati.twittersocial.model.Reels;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.service.ReelsService;
import com.mati.twittersocial.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;


    @PostMapping("/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reel,reqUser);

        return createdReels;
    }

    @GetMapping("/reels")
    public List<Reels> findAllReels() {

        List<Reels> reels = reelsService.findAllReels();

        return reels;
    }

    @GetMapping("/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable("userId") Integer userId) throws Exception {

        List<Reels> reels = reelsService.findUsersReel(userId);

        return reels;
    }


}
