package com.mati.twittersocial.controller;

import com.mati.twittersocial.model.Chat;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.request.CreateChatRequest;
import com.mati.twittersocial.service.ChatService;
import com.mati.twittersocial.service.ChatServiceImpl;
import com.mati.twittersocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;


    @PostMapping("/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,  @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);

        return chat;
    }

    @GetMapping("/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findUsersChat(reqUser.getId());

        return chats;
    }
}
