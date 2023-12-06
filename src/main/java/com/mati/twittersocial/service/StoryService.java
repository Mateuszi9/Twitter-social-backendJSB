package com.mati.twittersocial.service;

import com.mati.twittersocial.model.Story;
import com.mati.twittersocial.model.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
