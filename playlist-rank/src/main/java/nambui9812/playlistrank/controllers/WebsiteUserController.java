package nambui9812.playlistrank.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

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
    res.put("messages", Arrays.asList("Get all users successfully."));
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
    res.put("messages", Arrays.asList("Get user successfully."));
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
    res.put("messages", Arrays.asList("Get user successfully."));
    res.put("user", user);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Create a new user
  @PostMapping("/sign-up")
  ResponseEntity<Object> createUser(@Valid @RequestBody WebsiteUser newUser) {
    HashMap<String, Object> res = new HashMap<>();

    newUser = websiteUserServiceImpl.createWebsiteUser(newUser);

    res.put("success", true);
    res.put("messages", Arrays.asList("Create a new user successfully."));
    res.put("user", newUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Update a user
  @PutMapping("/update-info")
  ResponseEntity<Object> updateUser(@Valid @RequestBody UpdateWebsiteUserValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser existing = websiteUserServiceImpl.findById(info.getId());

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("success", false);
      res.put("messages", Arrays.asList("Unauthorization for updating user."));

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.updateWebsiteUser(existing, info);

    res.put("success", true);
    res.put("messages", Arrays.asList("Update user info successfully."));
    res.put("user", updated);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Follow a person
  @PutMapping("/follow-user")
  ResponseEntity<Object> followUser(@Valid @RequestBody FollowWebsiteUserValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser existing = websiteUserServiceImpl.findByUsername(info.getUsername());

    WebsiteUser follow = websiteUserServiceImpl.findByUsername(info.getFollowUsername());

    if (follow == null) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Invalid follow's username."));
      
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("success", false);
      res.put("messages", Arrays.asList("Unauthorization for following user."));

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.followWebsiteUser(existing, follow);

    res.put("success", true);
    res.put("messages", Arrays.asList("Follow other user successfully."));
    res.put("user", updated);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Unfollow a person
  @PutMapping("/unfollow-user")
  ResponseEntity<Object> unfollowUser(@Valid @RequestBody FollowWebsiteUserValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    WebsiteUser existing = websiteUserServiceImpl.findByUsername(info.getUsername());

    WebsiteUser follow = websiteUserServiceImpl.findByUsername(info.getFollowUsername());

    if (follow == null) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Invalid follow's username."));
      
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("success", false);
      res.put("messages", Arrays.asList("Unauthorization for unfollowing user."));

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    WebsiteUser updated = websiteUserServiceImpl.followWebsiteUser(existing, follow);

    res.put("success", true);
    res.put("messages", Arrays.asList("Unfollow other user successfully."));
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
      res.put("messages", Arrays.asList("Cannot delete an invalid user."));

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username from id in info
    String fromExisting = existing.getUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("success", false);
      res.put("messages", Arrays.asList("Unauthorization for deleting user."));

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    websiteUserServiceImpl.deleteWebsiteUser(id);

    res.put("success", true);
    res.put("messages", Arrays.asList("Delete user successfully."));

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}
