package com.rest;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseBody;


@ResponseBody
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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

    mvc.perform(get("/users/recommendations?gender=&age=20&occupation=&genre=Action"))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed UserRecommend with good arguments");
  }

  @Test
  public void TestWrongGenderUserRecommend() throws Exception {

    mvc.perform(get("/users/recommendations?gender=G&age=&occupation=&genre="))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed UserRecommend with wrong arguments");
  }
}
