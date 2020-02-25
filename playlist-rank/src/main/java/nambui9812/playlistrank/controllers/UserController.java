package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nambui9812.playlistrank.entities.User;
import nambui9812.playlistrank.repositories.UserRepository;
import nambui9812.playlistrank.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  // Constructor
  public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  // Get all users
  @GetMapping("/")
  ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  // Get a user by id
  @GetMapping("/{id}")
  ResponseEntity<User> getUser(@PathVariable String id) {
    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  // Create a new user
  @PostMapping("/sign-up")
  ResponseEntity<User> createUser(@RequestBody User newUser) {
    newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

    userRepository.save(newUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  // Update a user
  @PutMapping("/update/{id}")
  ResponseEntity<User> updateUser(@RequestBody User newUser) {
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

    return ResponseEntity.status(HttpStatus.OK).body(existing);
  }

  // Delete a user
  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id) {
    userRepository.deleteById(id);
  }
}