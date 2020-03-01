package nambui9812.playlistrank.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {
  List<Tag> findByName(String name);
  List<Tag> findByPlaylistId(String id);
}
