package nambui9812.playlistrank.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Comment;
import nambui9812.playlistrank.repositories.CommentRepository;
import nambui9812.playlistrank.exceptions.CommentNotFoundException;

@RestController
public class CommentController {
  private final CommentRepository commentRepository;

  // Constructor
  public CommentController(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  // Get all comments
  @GetMapping("/comments")
  List<Comment> getAllComments() {
    return commentRepository.findAll();
  }

  // Get a comment by id
  @GetMapping("/comments/{id}")
  Comment getComment(@PathVariable String id) {
    return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException());
  }

  // Create a new comment
  @PostMapping("/comments")
  Comment createComment(@RequestBody Comment newComment) {
    return commentRepository.save(newComment);
  }

  // Update a comment
  @PutMapping("/comments/{id}")
  Comment updateComment(@RequestBody Comment newComment) {
    Comment existing = commentRepository.findById(newComment.getId()).orElseThrow(() -> new CommentNotFoundException());

    return commentRepository.save(existing);
  }

  // Delete a comment
  @DeleteMapping("/comments/{id}")
  void deleteComment(@PathVariable String id) {
    commentRepository.deleteById(id);
  }
}