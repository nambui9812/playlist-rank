package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.User;
import nambui9812.playlistrank.repositories.UserRepository;

@RestController
public class UserController {
  private final UserRepository userRepository;

  // Constructor
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Get all users
  @GetMapping("/users")
  List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Get a user by id
  @GetMapping("/users/{id}")
  User getUser(@PathVariable String id) {
    return userRepository.findById(id).orElseThrow(() -> new Error());
  }

  // Create a new user
  @PostMapping("/users")
  User createUser(@RequestBody User newUser) {
    return userRepository.save(newUser);
  }

  // Update a user
  @PutMapping("/users/{id}")
  User updateUser(@RequestBody User newUser) {
    User existing = userRepository.findById(newUser.getId()).orElseThrow(() -> new Error());

    return userRepository.save(existing);
  }

  // Delete a user
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable String id) {
    userRepository.deleteById(id);
  }
}