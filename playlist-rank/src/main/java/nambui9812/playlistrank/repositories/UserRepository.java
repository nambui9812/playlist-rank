package nambui9812.playlistrank.repositories;

import org.springframework.data.mongodb.repository.*;

import nambui9812.playlistrank.entities.*;

public interface UserRepository extends MongoRepository<User, String> {
  public User findByEmail(String email);
  public User findByUsername(String username);
}
