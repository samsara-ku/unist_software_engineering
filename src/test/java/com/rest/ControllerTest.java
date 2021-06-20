package com.rest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void TestHandleError() throws Exception {
    mvc.perform(get("/error"))
        .andExpect(status().isOk());
    System.out.println("passed CustomErrorController_handleError1");
  }

  @Test
  public void TestGetErrorPath() throws Exception {
    CustomErrorController testController = new CustomErrorController();
    assertNull(testController.getErrorPath());
    System.out.println("passed CustomErrorController_getErrorPath");
  }
}
