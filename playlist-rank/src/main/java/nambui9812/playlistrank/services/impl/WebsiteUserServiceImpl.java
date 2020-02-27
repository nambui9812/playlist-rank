package nambui9812.playlistrank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;
import nambui9812.playlistrank.services.WebsiteUserService;
import nambui9812.playlistrank.exceptions.WebsiteUserNotFoundException;

@Service
public class WebsiteUserServiceImpl implements WebsiteUserService {
  @Autowired
  private WebsiteUserRepository websiteUserRepository;

  @Override
  public List<WebsiteUser> findAll() {
    return websiteUserRepository.findAll();
  }

  @Override
  public WebsiteUser findById(String id) {
    return websiteUserRepository.findById(id).orElseThrow(() -> new WebsiteUserNotFoundException());
  }

  @Override
  public WebsiteUser findByUsername(String username) {
    return websiteUserRepository.findByUsername(username);
  }

  @Override
  public WebsiteUser findByEmail(String email) {
    return websiteUserRepository.findByEmail(email);
  }

  @Override
  public void saveOrUpdateWebsiteUser(WebsiteUser websiteUser) {
    websiteUserRepository.save(websiteUser);
  }

  @Override
  public void deleteWebsiteUser(String id) {
    websiteUserRepository.deleteById(id);
  }
}