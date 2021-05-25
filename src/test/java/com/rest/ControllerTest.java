package com.rest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@WebMvcTest()
public class ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void TestHandleError() throws Exception {
    mvc.perform(get("/error"))
        .andExpect(status().isOk())
        .andExpect(content().string("Please input the appropriate json format and data type."));
    System.out.println("passed CustomErrorController_handleError1");
  }

  @Test
  public void TestHandleErrorWithReq() throws Exception {
    mvc.perform(get("/errorWithReq"))
        .andExpect(status().isOk())
        .andExpect(content().string("Please input the appropriate json format and data type."));
    System.out.println("passed CustomErrorController_handleError2");
  }

  @Test
  public void TestGetErrorPath() throws Exception {
    CustomErrorController testController = new CustomErrorController();
    assertNull(testController.getErrorPath());
    System.out.println("passed CustomErrorController_getErrorPath");
  }
}
