package com.mati.twittersocial.controller;

import com.mati.twittersocial.exceptions.UserException;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.repository.UserRepository;
import com.mati.twittersocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users=userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException {
        User user1 = userService.findUserById(id);
        return user1;
    }

    @PutMapping("/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);

        User updatedUser = userService.updateUser(user, reqUser.getId());

        return updatedUser;
    }


    @PutMapping("/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users = userService.searchUser(query);

        return users;
    }

    @GetMapping("/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }



}
