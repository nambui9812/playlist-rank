package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;
import nambui9812.playlistrank.exceptions.WebsiteUserNotFoundException;

@RestController
@RequestMapping("/users")
public class WebsiteUserController {
  private final WebsiteUserRepository websiteUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  // Constructor
  public WebsiteUserController(WebsiteUserRepository websiteUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.websiteUserRepository = websiteUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  // Get all users
  @GetMapping("/")
  ResponseEntity<List<WebsiteUser>> getAllUsers() {
    List<WebsiteUser> users = websiteUserRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  // Get a user by id
  @GetMapping("/{id}")
  ResponseEntity<WebsiteUser> getUser(@PathVariable String id) {
    WebsiteUser user = websiteUserRepository.findById(id).orElseThrow(() -> new WebsiteUserNotFoundException());

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  // Create a new user
  @PostMapping("/sign-up")
  ResponseEntity<WebsiteUser> createUser(@RequestBody WebsiteUser newUser) {
    newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

    websiteUserRepository.save(newUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  // Update a user
  @PutMapping("/update/{id}")
  ResponseEntity<WebsiteUser> updateUser(@RequestBody WebsiteUser newUser) {
    WebsiteUser existing = websiteUserRepository.findById(newUser.getId()).orElseThrow(() -> new WebsiteUserNotFoundException());

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
    websiteUserRepository.deleteById(id);
  }
}