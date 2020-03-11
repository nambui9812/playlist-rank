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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class WebsiteUserController {
  @Autowired
  private WebsiteUserServiceImpl websiteUserServiceImpl;

  // Get all users
  @GetMapping("/")
  ResponseEntity<Object> getAllUsers() {
    HashMap<String, Object> res = new HashMap<>();

    List<WebsiteUser> users = websiteUserServiceImpl.findAll();

    res.put("success", true);
    res.put("message", "Get all users successfully.");
    res.put("users", users);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get a user by id
  @GetMapping("/id/{id}")
  ResponseEntity<Object> getUser(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser user = websiteUserServiceImpl.findById(id);

    user.setPassword(null);

    res.put("success", true);
    res.put("message", "Get user successfully");
    res.put("user", user);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get a user by username
  @GetMapping("/username/{username}")
  ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser user = websiteUserServiceImpl.findByUsername(username);

    user.setPassword(null);

    res.put("success", true);
    res.put("message", "Get user successfully");
    res.put("user", user);

    return ResponseEntity.status(HttpStatus.OK).body(res);
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
      res.put("success", false);
      res.put("messages", messages);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    res.put("success", true);
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

      res.put("success", false);
      res.put("message", "Unauthorization for updating user.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.updateWebsiteUser(existing, info);

    res.put("success", true);
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
      res.put("success", false);
      res.put("message", "Invalid follow's username.");
      
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("success", false);
      res.put("message", "Unauthorization for following user.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.followWebsiteUser(existing, follow);

    res.put("success", true);
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
      res.put("success", false);
      res.put("message", "Cannot delete an invalid user.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("success", false);
      res.put("message", "Unauthorization for deleting user.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    websiteUserServiceImpl.deleteWebsiteUser(id);

    res.put("success", true);
    res.put("message", "Delete user successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}
