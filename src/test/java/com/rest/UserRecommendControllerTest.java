package com.rest;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseBody;


@ResponseBody
@WebMvcTest(UserRecommendController.class)
public class UserRecommendControllerTest {

  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void TestUserRecommend() throws Exception {

    String content = objectMapper
        .writeValueAsString(new UserRecommend("F", "15", "Educator", "Adventure"));

    mvc.perform(get("/users/recommendations")
        .content(content)
        .contentType(contentType))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed UserRecommend with good arguments");
  }

  @Test
  public void TestWrongGenderUserRecommend() throws Exception {

    String content = objectMapper
        .writeValueAsString(new UserRecommend("G", "15", "Educator", "Adventure"));

    mvc.perform(get("/users/recommendations")
        .content(content)
        .contentType(contentType))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed UserRecommend with wrong arguments");
  }
}
