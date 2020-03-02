package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.services.impl.WebsiteUserServiceImpl;
import nambui9812.playlistrank.validations.UpdateWebsiteUserValidation;
import nambui9812.playlistrank.validations.FollowWebsiteUserValidation;

@RestController
@RequestMapping("/users")
public class WebsiteUserController {
  @Autowired
  private WebsiteUserServiceImpl websiteUserServiceImpl;

  // Get all users
  @GetMapping("/")
  ResponseEntity<Object> getAllUsers() {
    List<WebsiteUser> users = websiteUserServiceImpl.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  // Get a user by id
  @GetMapping("/{id}")
  ResponseEntity<Object> getUser(@PathVariable String id) {
    WebsiteUser user = websiteUserServiceImpl.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  // Create a new user
  @PostMapping("/sign-up")
  ResponseEntity<Object> createUser(@RequestBody WebsiteUser newUser) throws Exception {
    HashMap<String, Object> res = new HashMap<>();

    try {

      newUser = websiteUserServiceImpl.createWebsiteUser(newUser);

    } catch (ConstraintViolationException e) {

      HashMap<String, Object> messages = new HashMap<>();
      e.getConstraintViolations().stream().forEach((violation) -> {
        messages.put(violation.getPropertyPath().toString(), violation.getMessage());
      });
      res.put("error", true);
      res.put("messages", messages);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    res.put("message", "Create a new user successfully");
    res.put("user", newUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Update a user
  @PutMapping("/update-info")
  ResponseEntity<Object> updateUser(@RequestBody UpdateWebsiteUserValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser existing = websiteUserServiceImpl.findById(info.getId());

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("message", "Unauthorization for updating user.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.updateWebsiteUser(existing, info);

    res.put("message", "Update user info successfully.");
    res.put("user", updated);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Follow a person
  @PutMapping("/follow-user")
  ResponseEntity<Object> followUser(@RequestBody FollowWebsiteUserValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser existing = websiteUserServiceImpl.findById(info.getId());

    WebsiteUser follow = websiteUserServiceImpl.findByUsername(info.getFollowUsername());

    if (follow == null) {
      res.put("message", "Invalid follow's username.");
      res.put("error", true);
      
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("message", "Unauthorization for following user.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.followWebsiteUser(existing, follow);

    res.put("message", "Follow other user successfully.");
    res.put("user", updated);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Delete a user
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteUser(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser existing = websiteUserServiceImpl.findById(id);

    if (existing == null) {
      res.put("message", "Cannot delete an invalid user.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("message", "Unauthorization for deleting user.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    websiteUserServiceImpl.deleteWebsiteUser(id);

    res.put("message", "Delete user successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}