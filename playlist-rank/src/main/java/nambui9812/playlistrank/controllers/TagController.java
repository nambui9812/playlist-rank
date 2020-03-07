package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.services.impl.TagServiceImpl;

@RestController
@RequestMapping("/tags")
public class TagController {
  @Autowired
  private TagServiceImpl tagServiceImpl;

  // Get all tags
  @GetMapping("/")
  ResponseEntity<Object> getAllTags() {
    List<Tag> tags = tagServiceImpl.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(tags);
  }

  // Get tag groups
  ResponseEntity<Object> getGroupsOfTag() {
    HashMap<String, Integer> res = tagServiceImpl.findGroupTagName();

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get a tag by id
  @GetMapping("/id/{id}")
  ResponseEntity<Object> getTag(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    Tag tag = tagServiceImpl.findById(id);

    res.put("message", "Get tag successfully.");
    res.put("tag", tag);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get all tags of a playlist
  @GetMapping("/id/{id}")
  ResponseEntity<Object> getAllTagsOfPlaylist(@PathVariable String playlistId) {
    HashMap<String, Object> res = new HashMap<>();

    List<Tag> tags = tagServiceImpl.findByPlaylistId(playlistId);

    res.put("message", "Get all tags of a playlist successfully.");
    res.put("tags", tags);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Create a new tag
  @PostMapping("/")
  ResponseEntity<Object> createTag(@RequestBody Tag newTag, Authentication authentication) throws Exception {
    HashMap<String, Object> res = new HashMap<>();

    try {

      if (!newTag.getAuthorUsername().equals(authentication.getName())) {
        res.put("error", true);
        res.put("message", "Cannot create tag for other person.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
      }

      newTag = tagServiceImpl.createTag(newTag);

    } catch (ConstraintViolationException e) {

      HashMap<String, Object> messages = new HashMap<>();
      e.getConstraintViolations().stream().forEach((violation) -> {
        messages.put(violation.getPropertyPath().toString(), violation.getMessage());
      });
      res.put("error", true);
      res.put("messages", messages);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    res.put("message", "Create a new tag successfully");
    res.put("tag", newTag);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Like a tag
  @PutMapping("/like/{id}")
  ResponseEntity<Object> likeTag(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Tag existing = tagServiceImpl.findById(id);

    if (existing == null) {
      res.put("message", "Cannot like an invalid tag.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Tag liked = tagServiceImpl.likeTag(authentication.getName(), existing);

    res.put("message", "Like a tag successfully.");
    res.put("tag", liked);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Dislike a tag
  @PutMapping("/dislike/{id}")
  ResponseEntity<Object> dislikeTag(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Tag existing = tagServiceImpl.findById(id);

    if (existing == null) {
      res.put("message", "Cannot dislike an invalid tag.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Tag disliked = tagServiceImpl.dislikeTag(authentication.getName(), existing);

    res.put("message", "Dislike a tag successfully.");
    res.put("tag", disliked);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Delete a tag
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteTag(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Tag existing = tagServiceImpl.findById(id);

    if (existing == null) {
      res.put("message", "Cannot delete an invalid tag.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username of owner
    String fromExisting = existing.getAuthorUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {

      res.put("message", "Unauthorization for deleting tag.");
      res.put("error", true);

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    tagServiceImpl.deleteTag(id);

    res.put("message", "Delete tag successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}