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
}
