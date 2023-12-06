package com.mati.twittersocial.controller;

import com.mati.twittersocial.model.Story;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.service.StoryService;
import com.mati.twittersocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/story")
    public Story createdStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);

        Story createdStory = storyService.createStory(story, reqUser);

        return createdStory;
    }

    @GetMapping("/story/user/{userId}")
    public List<Story> findUusersStory(@PathVariable("userId") Integer userId) throws Exception {
        List<Story> stories = storyService.findStoryByUserId(userId);

        return stories;
    }
}
