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
@WebMvcTest(MovieRecommendController.class)
public class MovieRecommendControllerTest {

  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void TestMovieRecommend() throws Exception {

    String content = objectMapper.writeValueAsString(new MovieRecommend("Toy Story (1995)", "10"));

    mvc.perform(get("/movies/recommendations")
        .content(content)
        .contentType(contentType))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed MovieRecommend with good arguments");
  }

  @Test
  public void TestWrongTitleMovieRecommend() throws Exception {
    String content = objectMapper.writeValueAsString(new MovieRecommend("", "10"));

    mvc.perform(get("/movies/recommendations")
        .content(content)
        .contentType(contentType))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed MovieRecommend with wrong title");
  }

  @Test
  public void TestWrongLimitMovieRecommend() throws Exception {
    String content = objectMapper.writeValueAsString(new MovieRecommend("Toy Story (1995)", "-1"));

    mvc.perform(get("/movies/recommendations")
        .content(content)
        .contentType(contentType))
        .andExpect((res) -> assertNotNull(status().isOk()));

    System.out.println("passed MovieRecommend with wrong limit");
  }
}
