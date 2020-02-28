package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.services.impl.WebsiteUserServiceImpl;

@RestController
@RequestMapping("/users")
public class WebsiteUserController {
  @Autowired
  private WebsiteUserServiceImpl websiteUserServiceImpl;

  // Get all users
  @GetMapping("/")
  ResponseEntity<List<WebsiteUser>> getAllUsers() {
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
  @PutMapping("/update/{id}")
  ResponseEntity<Object> updateUser(@RequestBody WebsiteUser newUser) {
    WebsiteUser existing = websiteUserServiceImpl.findById(newUser.getId());

    WebsiteUser updated = websiteUserServiceImpl.updateWebsiteUser(existing, newUser);

    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  // Delete a user
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteUser(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    if (id == null) {
      res.put("message", "Cannot delete user.");
    }
    else {
      websiteUserServiceImpl.deleteWebsiteUser(id);

      res.put("message", "Delete user successfully.");
    }

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}