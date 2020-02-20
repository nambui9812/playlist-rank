package nambui9812.playlistrank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
  public User findByEmail(String email);
  public User findByUsername(String username);
}
