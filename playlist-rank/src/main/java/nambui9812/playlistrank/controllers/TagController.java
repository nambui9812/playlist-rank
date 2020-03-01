package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  // Get a tag by id
  @GetMapping("/{id}")
  ResponseEntity<Object> getTag(@PathVariable String id) {
    Tag tag = tagServiceImpl.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(tag);
  }

  // Create a new tag
  @PostMapping("/")
  ResponseEntity<Object> createTag(@RequestBody Tag newTag) throws Exception {
    HashMap<String, Object> res = new HashMap<>();

    try {

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

  // Delete a tag
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteTag(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    if (id == null) {
      res.put("message", "Cannot delete tag.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
    
    tagServiceImpl.deleteTag(id);

    res.put("message", "Delete tag successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}