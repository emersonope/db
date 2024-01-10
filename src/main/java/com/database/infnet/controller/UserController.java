package com.database.infnet.controller;

import java.util.List;

import com.database.infnet.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  Logger logger = LoggerFactory.getLogger(UserController.class);

  @Operation(description = "Return a list of users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Return the user"),
          @ApiResponse(responseCode = "400", description = "User do not exist")
  })
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    logger.info("Return all clients: " + users);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("server-name", nickname);
    return ResponseEntity.ok().headers(httpHeaders).body(users);
  }

  @Operation(description = "Return a list of users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Return the user by ID"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "400", description = "The server rejects the request, deeming it a client error.")
  })
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    try {
      User user = userService.getUserById(id);
      logger.info("Return Selected User: " + user);
      return ResponseEntity.ok(user);
    } catch (UserNotFoundException ex) {
      ex.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(description = "Return a list of users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "User was created"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "400", description = "The server rejects the request, deeming it a client error.")
  })
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User newUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  @Operation(description = "Return a list of users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "User updated"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "400", description = "The server rejects the request, deeming it a client error.")
  })
  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {

   
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id) {
    return userService.deleteUser(id);
  }
  
}