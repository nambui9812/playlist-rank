package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.validations.UpdateWebsiteUserValidation;

public interface WebsiteUserService {
  List<WebsiteUser> findAll();

  WebsiteUser findById(String id);

  WebsiteUser findByUsername(String username);

  WebsiteUser findByEmail(String email);

  WebsiteUser createWebsiteUser(WebsiteUser websiteUser);

  WebsiteUser updateWebsiteUser(WebsiteUser existing, UpdateWebsiteUserValidation info);

  WebsiteUser followWebsiteUser(WebsiteUser existing, WebsiteUser follow);

  WebsiteUser unfollowWebsiteUser(WebsiteUser existing, WebsiteUser follow);

  void deleteWebsiteUser(String id);
}