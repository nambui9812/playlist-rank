package nambui9812.playlistrank.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
  List<Comment> findByAuthorUsername(String authorUsername);
  List<Comment> findByPlaylistId(String playlistId);
  List<Comment> findByToCommentId(String id);
}
