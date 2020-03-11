package nambui9812.playlistrank.controllers;

/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nambui9812.playlistrank.entities.WebsiteUser;
import nambui9812.playlistrank.services.impl.WebsiteUserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebsiteUserControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private WebsiteUserServiceImpl websiteUserServiceImpl;

  private HashMap<String, Object> successResponse = new HashMap<>();
  private HashMap<String, Object> errorResponse = new HashMap<>();

  private WebsiteUser nam1;
  private WebsiteUser nam2;
  private List<WebsiteUser> users = new ArrayList<>();

  @Test
  public void testGetAllUsers() throws Exception {
    nam1 = new WebsiteUser("nam1@gmail.com", "nam1", "nam1");
    nam2 = new WebsiteUser("nam2@gmail.com", "nam2", "nam2");

    users.add(nam1);
    users.add(nam2);

    successResponse.put("success", true);
    successResponse.put("message", "Get all users successfully.");
    successResponse.put("users", users);

    Mockito.when(websiteUserServiceImpl.findAll()).thenReturn(users);

    
    mvc.perform(get("/users/"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().json("hello"));
    

    MvcResult result = mvc.perform(get("/users.")).andReturn();

    int status = result.getResponse().getStatus();
    assertEquals(status, 200);
  }
}
*/