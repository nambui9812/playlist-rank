package nambui9812.playlistrank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {
  
}
