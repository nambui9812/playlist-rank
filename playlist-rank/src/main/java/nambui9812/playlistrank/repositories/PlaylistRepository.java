package nambui9812.playlistrank.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.Playlist;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
  List<Playlist> findByAuthorUsername(String username);
}
