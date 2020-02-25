package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Comment;
import nambui9812.playlistrank.repositories.CommentRepository;
import nambui9812.playlistrank.exceptions.CommentNotFoundException;

@RestController
@RequestMapping("/comments")
public class CommentController {
  private final CommentRepository commentRepository;

  // Constructor
  public CommentController(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  // Get all comments
  @GetMapping("/")
  ResponseEntity<List<Comment>> getAllComments() {
    List<Comment> comments = commentRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(comments);
  }

  // Get a comment by id
  @GetMapping("/{id}")
  ResponseEntity<Comment> getComment(@PathVariable String id) {
    Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException());

    return ResponseEntity.status(HttpStatus.OK).body(comment);
  }

  // Create a new comment
  @PostMapping("/")
  ResponseEntity<Comment> createComment(@RequestBody Comment newComment) {
    commentRepository.save(newComment);

    return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
  }

  // Update a comment
  @PutMapping("/{id}")
  ResponseEntity<Comment> updateComment(@RequestBody Comment newComment) {
    Comment existing = commentRepository.findById(newComment.getId()).orElseThrow(() -> new CommentNotFoundException());

    return ResponseEntity.status(HttpStatus.OK).body(existing);
  }

  // Delete a comment
  @DeleteMapping("/{id}")
  void deleteComment(@PathVariable String id) {
    commentRepository.deleteById(id);
  }
}