package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Comment;
import nambui9812.playlistrank.services.impl.CommentServiceImpl;

@RestController
@RequestMapping("/comments")
public class CommentController {
  @Autowired
  private CommentServiceImpl commentServiceImpl;

  // Get all comments
  @GetMapping("/")
  ResponseEntity<Object> getAllComments() {
    List<Comment> comments = commentServiceImpl.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(comments);
  }

  // Get a comment by id
  @GetMapping("/{id}")
  ResponseEntity<Object> getComment(@PathVariable String id) {
    Comment comment = commentServiceImpl.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(comment);
  }

  // Create a new comment
  @PostMapping("/")
  ResponseEntity<Object> createComment(@RequestBody Comment newComment) throws Exception {
    HashMap<String, Object> res = new HashMap<>();

    try {

      newComment = commentServiceImpl.createComment(newComment);

    } catch (ConstraintViolationException e) {

      HashMap<String, Object> messages = new HashMap<>();
      e.getConstraintViolations().stream().forEach((violation) -> {
        messages.put(violation.getPropertyPath().toString(), violation.getMessage());
      });
      res.put("error", true);
      res.put("messages", messages);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    res.put("message", "Create a new comment successfully");
    res.put("commment", newComment);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Delete a comment
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteComment(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    if (id == null) {
      res.put("message", "Cannot delete comment.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
    
    commentServiceImpl.deleteComment(id);

    res.put("message", "Delete comment successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}