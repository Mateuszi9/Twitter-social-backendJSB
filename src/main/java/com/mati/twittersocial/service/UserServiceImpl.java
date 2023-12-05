package com.mati.twittersocial.service;

import com.mati.twittersocial.model.User;
import com.mati.twittersocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            return user.get();
        }

        throw new Exception("user not exist with userId " + userId);
    }

    @Override
    public User findUserByEmile(String email) {
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, Integer id) throws Exception {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()){
            throw new Exception("user not exist with id " + id);
        }

        User oldUser = user1.get();
        if (user.getFirstName()!=null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!=null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail()!=null) {
            oldUser.setEmail(user.getEmail());
        }
        User updateUser = userRepository.save(oldUser);
        return updateUser;
    }

    @Override
    public List<User> searchUser(String query) {


        return userRepository.searchUser(query);
    }
}
