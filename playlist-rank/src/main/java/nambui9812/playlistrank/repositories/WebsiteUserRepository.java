package nambui9812.playlistrank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import nambui9812.playlistrank.entities.WebsiteUser;

public interface WebsiteUserRepository extends MongoRepository<WebsiteUser, String> {
  public WebsiteUser findByEmail(String email);
  public WebsiteUser findByUsername(String username);
}
