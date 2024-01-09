package com.database.infnet.controller;

import java.util.List;

import com.database.infnet.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.infnet.model.User;
import com.database.infnet.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/")
public class UserController {

  @Value("${spring.application.servernick}")
  private String nickname;
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("server-name", nickname);
    return ResponseEntity.ok().headers(httpHeaders).body(users);
  } 

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    try {
      User user = userService.getUserById(id);
      return ResponseEntity.ok(user);
    } catch (UserNotFoundException ex) {
      ex.printStackTrace();
      ResponseEntity.notFound().build();
    }
    return null;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User newUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }
  
  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {

   
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id) {
    return userService.deleteUser(id);
  }
  
}