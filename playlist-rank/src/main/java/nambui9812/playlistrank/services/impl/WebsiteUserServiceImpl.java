package nambui9812.playlistrank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;
import nambui9812.playlistrank.services.WebsiteUserService;
import nambui9812.playlistrank.exceptions.WebsiteUserNotFoundException;

@Service
public class WebsiteUserServiceImpl implements WebsiteUserService {
  @Autowired
  private WebsiteUserRepository websiteUserRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

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
  public WebsiteUser createWebsiteUser(WebsiteUser websiteUser) {
    websiteUser.setPassword(bCryptPasswordEncoder.encode(websiteUser.getPassword()));

    return websiteUserRepository.save(websiteUser);
  }

  @Override
  public WebsiteUser updateWebsiteUser(WebsiteUser existing, WebsiteUser newUser) {

    existing.setEmail(newUser.getEmail());
    existing.setDateOfBirth(newUser.getDateOfBirth());
    existing.setMonthOfBirth(newUser.getMonthOfBirth());
    existing.setYearOfBirth(newUser.getYearOfBirth());
    existing.setStreet(newUser.getStreet());
    existing.setOptionalStreet(newUser.getOptionalStreet());
    existing.setCity(newUser.getCity());
    existing.setProvince(newUser.getProvince());
    existing.setCountry(newUser.getCountry());
    existing.setZipcode(newUser.getZipcode());
    existing.setPhone(newUser.getPhone());
    existing.setStatus(newUser.getStatus());
    existing.setFollowingList(newUser.getFollowingList());
    existing.setFollowerList(newUser.getFollowerList());
    existing.setFollowRequests(newUser.getFollowRequests());
    existing.setAccountType(newUser.getAccountType());

    return websiteUserRepository.save(existing);
  }

  @Override
  public void deleteWebsiteUser(String id) {
    websiteUserRepository.deleteById(id);
  }
}