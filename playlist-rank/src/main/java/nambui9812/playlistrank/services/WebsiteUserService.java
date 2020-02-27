package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.WebsiteUser;

public interface WebsiteUserService {
  List<WebsiteUser> findAll();

  WebsiteUser findById(String id);

  WebsiteUser findByUsername(String username);

  WebsiteUser findByEmail(String email);

  void saveOrUpdateWebsiteUser(WebsiteUser websiteUser);

  void deleteWebsiteUser(String id);
}