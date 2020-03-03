package nambui9812.playlistrank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.Comment;
import nambui9812.playlistrank.repositories.CommentRepository;
import nambui9812.playlistrank.services.CommentService;
import nambui9812.playlistrank.exceptions.CommentNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired
  private CommentRepository commentRepository;

  @Override
  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  @Override
  public Comment findById(String id) {
    return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException());
  }

  @Override
  public List<Comment> findByAuthorUsername(String authorUsername) {
    return commentRepository.findByAuthorUsername(authorUsername);
  }

  @Override
  public List<Comment> findByPlaylistId(String playlistId) {
    return commentRepository.findByPlaylistId(playlistId);
  }

  @Override
  public List<Comment> findByToCommentId(String commentId) {
    return commentRepository.findByToCommentId(commentId);
  }

  @Override
  public Comment createComment(Comment comment) {
    return commentRepository.save(comment);
  }

  @Override
  public void deleteComment(Comment existing) {
    if (existing.getToCommentId() != null && !existing.getDeleted()) {
      existing.setContent("xX This comment has been deleted. Xx");
      existing.setDeleted(true);

      commentRepository.save(existing);
      return;
    }

    commentRepository.deleteById(existing.getId());
  }
}