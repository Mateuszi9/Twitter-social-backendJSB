package com.mati.twittersocial.controller;

import com.mati.twittersocial.model.Message;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.service.MessageService;
import com.mati.twittersocial.service.UserService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreateMessage {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    @PostMapping("/messages/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt,
                                 @RequestBody Message message,
                                 @PathVariable("chatId") Integer chatId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        return messageService.createMessage(reqUser,chatId,message);
    }

    @GetMapping("/messages/chat/{chatId}")
    public List<Message> findChatMessages(@RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        return messageService.findChatMessages(chatId);
    }


}
