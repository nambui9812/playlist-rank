package nambui9812.playlistrank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;
import nambui9812.playlistrank.services.WebsiteUserService;
import nambui9812.playlistrank.exceptions.WebsiteUserNotFoundException;
import nambui9812.playlistrank.validations.UpdateWebsiteUserValidation;

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
  public WebsiteUser updateWebsiteUser(WebsiteUser existing, UpdateWebsiteUserValidation info) {

    if (info.getFirstName() != null) existing.setFirstName(info.getFirstName());

    if (info.getLastName() != null) existing.setLastName(info.getLastName());

    if (info.getDateOfBirth() != null) existing.setDateOfBirth(info.getDateOfBirth());

    if (info.getMonthOfBirth() != null) existing.setMonthOfBirth(info.getMonthOfBirth());

    if (info.getYearOfBirth() != null) existing.setYearOfBirth(info.getYearOfBirth());

    if (info.getStreet() != null) existing.setStreet(info.getStreet());

    if (info.getOptionalStreet() != null) existing.setOptionalStreet(info.getOptionalStreet());

    if (info.getCity() != null) existing.setCity(info.getCity());

    if (info.getProvince() != null) existing.setProvince(info.getProvince());

    if (info.getCountry() != null) existing.setCountry(info.getCountry());

    if (info.getZipcode() != null) existing.setZipcode(info.getZipcode());

    if (info.getPhone() != null) existing.setPhone(info.getPhone());

    if (info.getStatus() != null) existing.setStatus(info.getStatus());

    return websiteUserRepository.save(existing);
  }

  @Override
  public void deleteWebsiteUser(String id) {
    websiteUserRepository.deleteById(id);
  }
}