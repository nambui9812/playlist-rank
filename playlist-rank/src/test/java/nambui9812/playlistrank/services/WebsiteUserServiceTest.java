package nambui9812.playlistrank.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;
import nambui9812.playlistrank.services.impl.WebsiteUserServiceImpl;
import nambui9812.playlistrank.validations.UpdateWebsiteUserValidation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteUserServiceTest {

  @Autowired
  private WebsiteUserServiceImpl websiteUserServiceImpl;

  @MockBean
  WebsiteUserRepository websiteUserRepository;

  private WebsiteUser nam1;
  private WebsiteUser nam2;
  private ArrayList<WebsiteUser> users = new ArrayList<>();

  @Test
  public void testFindAll() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");
    nam2 = new WebsiteUser("nam2@gmail.com", "nam2", "nam2");

    users.add(nam1);
    users.add(nam2);

    Mockito.when(websiteUserRepository.findAll()).thenReturn(users);

    List<WebsiteUser> foundUsersFromService = websiteUserServiceImpl.findAll();

    assertNotNull(foundUsersFromService);
    assertEquals(2, foundUsersFromService.size());
  }

  @Test
  public void testFindById() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");

    Mockito.when(websiteUserRepository.findById(nam1.getId())).thenReturn(Optional.of(nam1));

    WebsiteUser found = websiteUserServiceImpl.findById(nam1.getId());

    assertNotNull(found);
    assertEquals(found.getId(), nam1.getId());
    assertEquals(found.getUsername(), nam1.getUsername());
  }

  @Test
  public void testFindByUsername() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");

    Mockito.when(websiteUserRepository.findByUsername(nam1.getUsername())).thenReturn(nam1);

    WebsiteUser found = websiteUserServiceImpl.findByUsername("nam1");

    assertNotNull(found);
    assertEquals(found.getId(), nam1.getId());
    assertEquals(found.getUsername(), nam1.getUsername());
  }

  @Test
  public void testFindByEmail() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");

    Mockito.when(websiteUserRepository.findByEmail(nam1.getEmail())).thenReturn(nam1);

    WebsiteUser found = websiteUserServiceImpl.findByEmail("nam1@gmail.com");

    assertNotNull(found);
    assertEquals(found.getId(), nam1.getId());
    assertEquals(found.getUsername(), nam1.getUsername());
  }

  @Test
  public void testCreateWebsiteUser() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");

    Mockito.when(websiteUserRepository.save(nam1)).thenReturn(nam1);

    WebsiteUser created = websiteUserServiceImpl.createWebsiteUser(nam1);

    assertNotNull(created);
    assertEquals(created.getId(), nam1.getId());
    assertEquals(created.getUsername(), nam1.getUsername());
  }

  @Test
  public void testUpdateWebsiteUser() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");

    UpdateWebsiteUserValidation info = new UpdateWebsiteUserValidation(nam1.getId(), "nam1", "bui", "15", "Feb", "1998", "street1", "optionalStreet1", "city1", "province1", "country1", "N1N 1N1", "012 345 6789", "status1");

    Mockito.when(websiteUserRepository.save(nam1)).thenReturn(nam1);

    WebsiteUser updated = websiteUserServiceImpl.updateWebsiteUser(nam1, info);

    assertNotNull(updated);
    assertEquals(updated.getId(), nam1.getId());
    assertEquals(updated.getEmail(), nam1.getEmail());
    assertEquals(updated.getUsername(), nam1.getUsername());
    assertEquals(updated.getFirstName(), "nam1");
    assertEquals(updated.getLastName(), "bui");
    assertEquals(updated.getDateOfBirth(), "15");
    assertEquals(updated.getMonthOfBirth(), "Feb");
    assertEquals(updated.getYearOfBirth(), "1998");
    assertEquals(updated.getStreet(), "street1");
    assertEquals(updated.getOptionalStreet(), "optionalStreet1");
    assertEquals(updated.getCity(), "city1");
    assertEquals(updated.getProvince(), "province1");
    assertEquals(updated.getCountry(), "country1");
    assertEquals(updated.getZipcode(), "N1N 1N1");
    assertEquals(updated.getPhone(), "012 345 6789");
    assertEquals(updated.getStatus(), "status1");
  }

  @Test
  public void testFollowWebsiteUser() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");
    nam2 = new WebsiteUser("nam2@gmail.com", "nam2", "nam2");

    Mockito.when(websiteUserRepository.save(nam1)).thenReturn(nam1);
    Mockito.when(websiteUserRepository.save(nam2)).thenReturn(nam2);

    WebsiteUser followed = websiteUserServiceImpl.followWebsiteUser(nam1, nam2);

    assertNotNull(followed);
    assertEquals(followed.getFollowingList().get(0), nam2.getUsername());
    assertEquals(nam2.getFollowerList().get(0), nam1.getUsername());
  }

  @Test
  public void testDeleteWebsiteUser() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");

    websiteUserServiceImpl.deleteWebsiteUser(nam1.getId());

    Mockito.verify(websiteUserRepository, Mockito.times(1)).deleteById(nam1.getId());
  }
}
