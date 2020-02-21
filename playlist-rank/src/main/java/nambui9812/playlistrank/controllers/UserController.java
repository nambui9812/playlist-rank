package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nambui9812.playlistrank.entities.User;
import nambui9812.playlistrank.repositories.UserRepository;
import nambui9812.playlistrank.exceptions.UserNotFoundException;

@RestController
public class UserController {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  // Constructor
  public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  // Get all users
  @GetMapping("/users")
  List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Get a user by id
  @GetMapping("/users/{id}")
  User getUser(@PathVariable String id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
  }

  // Create a new user
  @PostMapping("/users")
  User createUser(@RequestBody User newUser) {
    newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

    return userRepository.save(newUser);
  }

  // Update a user
  @PutMapping("/users/{id}")
  User updateUser(@RequestBody User newUser) {
    User existing = userRepository.findById(newUser.getId()).orElseThrow(() -> new UserNotFoundException());

    existing.setFirstName(newUser.getFirstName());
    existing.setLastName(newUser.getLastName());
    existing.setEmail(newUser.getEmail());
    existing.setUsername(newUser.getUsername());
    existing.setPassword(newUser.getPassword());
    existing.setDateOfBirth(newUser.getDateOfBirth());
    existing.setMonthOfBirth(newUser.getMonthOfBirth());
    existing.setYearOfBirth(newUser.getYearOfBirth());
    existing.setStreet(newUser.getStreet());
    existing.setOptionalStreet(newUser.getOptionalStreet());
    existing.setCity(newUser.getCity());
    existing.setProvince(newUser.getProvince());
    existing.setCountry(newUser.getCountry());
    existing.setZipcode(newUser.getZipcode());
    existing.setPhone(newUser.getPhone());
    existing.setStatus(newUser.getStatus());
    existing.setFollowingList(newUser.getFollowingList());
    existing.setFollowerList(newUser.getFollowerList());
    existing.setFollowRequests(newUser.getFollowRequests());
    existing.setAccountType(newUser.getAccountType());

    return userRepository.save(existing);
  }

  // Delete a user
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable String id) {
    userRepository.deleteById(id);
  }
}