package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.ResultActions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.nio.charset.*;

@ResponseBody
@WebMvcTest(MovieRecommendController.class)
public class MovieRecommendControllerTest {

	  @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    //MovieRecommendController
    @Test
	public void TestMovieRecommend() throws Exception {

        String content = objectMapper.writeValueAsString(new MovieRecommend("Toy Story (1995)", "10"));

        mvc.perform(get("/recommendations/movie")
                .content(content)
                .contentType(contentType))
                // .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        System.out.println("passed MovieRecommendController_recommend");
	}
}
