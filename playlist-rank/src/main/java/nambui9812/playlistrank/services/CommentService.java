package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.Comment;

public interface CommentService {
  List<Comment> findAll();

  Comment findById(String id);

  List<Comment> findByAuthorUsername(String authorUsername);

  List<Comment> findByPlaylistId(String playlistId);

  List<Comment> findByToCommentId(String commentId);

  Comment createComment(Comment comment);

  void deleteComment(Comment existing);
}