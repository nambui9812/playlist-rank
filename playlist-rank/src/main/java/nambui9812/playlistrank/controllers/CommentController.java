package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Comment;
import nambui9812.playlistrank.services.impl.CommentServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comments")
public class CommentController {
  @Autowired
  private CommentServiceImpl commentServiceImpl;

  // Get all comments
  @GetMapping("/")
  ResponseEntity<Object> getAllComments() {
    HashMap<String, Object> res = new HashMap<>();
    
    List<Comment> comments = commentServiceImpl.findAll();

    res.put("success", true);
    res.put("message", "Get all coomments successfully.");
    res.put("comments", comments);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get a comment by id
  @GetMapping("/id/{id}")
  ResponseEntity<Object> getComment(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    Comment comment = commentServiceImpl.findById(id);

    res.put("success", true);
    res.put("message", "Get comment successfully.");
    res.put("comment", comment);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get all comments in a playlist
  @GetMapping("/playlist/{id}")
  ResponseEntity<Object> getAllCommentsInPlaylist(@PathVariable String playlistId) {
    HashMap<String, Object> res = new HashMap<>();

    List<Comment> comments = commentServiceImpl.findByPlaylistId(playlistId);

    res.put("success", true);
    res.put("message", "Get all comments in a playlist successfully.");
    res.put("comments", comments);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get all comments response to a comment
  @GetMapping("/comment/{id}")
  ResponseEntity<Object> getAllCommentsResponseToComment(@PathVariable String toCommentId) {
    HashMap<String, Object> res = new HashMap<>();

    List<Comment> comments = commentServiceImpl.findByToCommentId(toCommentId);

    res.put("success", true);
    res.put("message", "Get all comments response to a comment successfully.");
    res.put("comments", comments);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Create a new comment
  @PostMapping("/")
  ResponseEntity<Object> createComment(@Valid @RequestBody Comment newComment, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    if (!newComment.getAuthorUsername().equals(authentication.getName())) {
      res.put("success", false);
      res.put("message", "Cannot create comment for other person.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    newComment = commentServiceImpl.createComment(newComment);

    res.put("success", true);
    res.put("message", "Create a new comment successfully");
    res.put("commment", newComment);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Delete a comment
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteComment(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Comment existing = commentServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("message", "Cannot delete an invalid comment.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username of owner
    String fromExisting = existing.getAuthorUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {
      res.put("success", false);
      res.put("message", "Unauthorization for deleting comment.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    commentServiceImpl.deleteComment(existing);

    res.put("success", true);
    res.put("message", "Delete comment successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}