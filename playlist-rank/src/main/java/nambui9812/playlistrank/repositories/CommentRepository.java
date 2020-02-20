package nambui9812.playlistrank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
  
}
