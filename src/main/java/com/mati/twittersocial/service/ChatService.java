package com.mati.twittersocial.service;

import com.mati.twittersocial.model.Chat;
import com.mati.twittersocial.model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}
