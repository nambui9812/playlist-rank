package nambui9812.playlistrank.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.repositories.WebsiteUserRepository;
import nambui9812.playlistrank.services.impl.WebsiteUserServiceImpl;

@RunWith(SpringRunner.class)
public class WebsiteUserServiceImplTest {
  @TestConfiguration
  public static class WebsiteUserServiceImplTestConfiguration {
    @Bean
    WebsiteUserServiceImpl websiteUserServiceImpl() {
      return new WebsiteUserServiceImpl();
    }
  }

  @Autowired
  private WebsiteUserServiceImpl websiteUserServiceImpl;

  @MockBean
  private WebsiteUserRepository websiteUserRepository;

  private WebsiteUser nam1;
  private WebsiteUser nam2;
  private List<WebsiteUser> users = new ArrayList<>();

  @Before
  public void setup() {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1123");  
    nam2 = new WebsiteUser("nam2@gmail.com", "nam2", "nam2123");
    
    users.add(nam1);
    users.add(nam2);

    Mockito.when(websiteUserRepository.findAll()).thenReturn(users);
  }

  @Test
  public void testFindAll() {
    List<WebsiteUser> foundWebsiteUsers = websiteUserServiceImpl.findAll();
    
    assertNotNull(foundWebsiteUsers);
    assertEquals(2, foundWebsiteUsers.size());
  }
}
