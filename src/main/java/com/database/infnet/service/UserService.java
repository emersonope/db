package com.database.infnet.service;

import java.util.List;

import com.database.infnet.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.infnet.model.User;
import com.database.infnet.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    } 


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    

    public User updateUser(Long id,User user) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }


    public String deleteUser(Long id) {
        try {
        userRepository.findById(id).get();
        userRepository.deleteById(id);
        return "User deleted successfully";
        } catch (Exception e) {
        return "User not found";
        }
    }
    
}
