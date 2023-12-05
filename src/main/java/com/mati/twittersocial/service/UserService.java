package com.mati.twittersocial.service;

import com.mati.twittersocial.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmile(String email);

    public User followUser(Integer userId1, Integer userId2) throws Exception;

    public User updateUser(User user, Integer id) throws Exception;

    public List<User> searchUser(String query);
}
