package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.services.impl.WebsiteUserServiceImpl;

@RestController
@RequestMapping("/users")
public class WebsiteUserController {
  private final WebsiteUserServiceImpl websiteUserServiceImpl;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  // Constructor
  public WebsiteUserController(WebsiteUserServiceImpl websiteUserServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.websiteUserServiceImpl = websiteUserServiceImpl;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  // Get all users
  @GetMapping("/")
  ResponseEntity<List<WebsiteUser>> getAllUsers() {
    List<WebsiteUser> users = websiteUserServiceImpl.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  // Get a user by id
  @GetMapping("/{id}")
  ResponseEntity<WebsiteUser> getUser(@PathVariable String id) {
    WebsiteUser user = websiteUserServiceImpl.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  // Create a new user
  @PostMapping("/sign-up")
  ResponseEntity<WebsiteUser> createUser(@RequestBody WebsiteUser newUser) {
    newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

    websiteUserServiceImpl.saveOrUpdateWebsiteUser(newUser);;

    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  // Update a user
  @PutMapping("/update/{id}")
  ResponseEntity<WebsiteUser> updateUser(@RequestBody WebsiteUser newUser) {
    WebsiteUser existing = websiteUserServiceImpl.findById(newUser.getId());

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

    websiteUserServiceImpl.saveOrUpdateWebsiteUser(existing);

    return ResponseEntity.status(HttpStatus.OK).body(existing);
  }

  // Delete a user
  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id) {
    websiteUserServiceImpl.deleteWebsiteUser(id);
  }
}