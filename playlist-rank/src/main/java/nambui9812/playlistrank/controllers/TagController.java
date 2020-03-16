package nambui9812.playlistrank.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.services.impl.TagServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tags")
public class TagController {
  @Autowired
  private TagServiceImpl tagServiceImpl;

  // Get all tags
  @GetMapping("/")
  ResponseEntity<Object> getAllTags() {
    HashMap<String, Object> res = new HashMap<>();

    List<Tag> tags = tagServiceImpl.findAll();

    res.put("success", true);
    res.put("messages", Arrays.asList("Get all tags successfully."));
    res.put("tags", tags);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get tag groups
  ResponseEntity<Object> getGroupsOfTag() {
    HashMap<String, Object> res = new HashMap<>();

    HashMap<String, Integer> group = tagServiceImpl.findGroupTagName();

    res.put("success", true);
    res.put("messages", Arrays.asList("Get group of tags successfully."));
    res.put("group", group);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get a tag by id
  @GetMapping("/tag/{id}")
  ResponseEntity<Object> getTag(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    Tag tag = tagServiceImpl.findById(id);

    res.put("success", true);
    res.put("messages", Arrays.asList("Get tag successfully."));
    res.put("tag", tag);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get all tags of a playlist
  @GetMapping("/playlist/{id}")
  ResponseEntity<Object> getAllTagsOfPlaylist(@PathVariable String playlistId) {
    HashMap<String, Object> res = new HashMap<>();

    List<Tag> tags = tagServiceImpl.findByPlaylistId(playlistId);

    res.put("success", true);
    res.put("messages", Arrays.asList("Get all tags of a playlist successfully."));
    res.put("tags", tags);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Create a new tag
  @PostMapping("/")
  ResponseEntity<Object> createTag(@Valid @RequestBody Tag newTag, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    if (!newTag.getAuthorUsername().equals(authentication.getName())) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Cannot create tag for other person."));

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    newTag = tagServiceImpl.createTag(newTag);

    res.put("success", true);
    res.put("messages", Arrays.asList("Create a new tag successfully"));
    res.put("tag", newTag);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Like a tag
  @PutMapping("/like/{id}")
  ResponseEntity<Object> likeTag(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Tag existing = tagServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Cannot like an invalid tag."));

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Tag liked = tagServiceImpl.likeTag(authentication.getName(), existing);

    res.put("success", true);
    res.put("messages", Arrays.asList("Like a tag successfully."));
    res.put("tag", liked);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Dislike a tag
  @PutMapping("/dislike/{id}")
  ResponseEntity<Object> dislikeTag(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Tag existing = tagServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Cannot dislike an invalid tag."));

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Tag disliked = tagServiceImpl.dislikeTag(authentication.getName(), existing);

    res.put("success", true);
    res.put("messages", Arrays.asList("Dislike a tag successfully."));
    res.put("tag", disliked);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Delete a tag
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteTag(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Tag existing = tagServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Cannot delete an invalid tag."));

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username of owner
    String fromExisting = existing.getAuthorUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {
      res.put("success", false);
      res.put("messages", Arrays.asList("Unauthorization for deleting tag."));

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    tagServiceImpl.deleteTag(id);

    res.put("success", true);
    res.put("messages", Arrays.asList("Delete tag successfully."));

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}
