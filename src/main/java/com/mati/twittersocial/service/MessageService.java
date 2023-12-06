package com.mati.twittersocial.service;

import com.mati.twittersocial.model.Chat;
import com.mati.twittersocial.model.Message;
import com.mati.twittersocial.model.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatMessages(Integer chatId) throws Exception;

}
