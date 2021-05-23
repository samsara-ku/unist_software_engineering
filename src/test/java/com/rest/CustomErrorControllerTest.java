package com.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

import org.junit.Test;

public class CustomErrorControllerTest {

  @Test
  public void testHandleError() {
    CustomErrorController test = new CustomErrorController();
    
    //assertEquals("Please input the appropriate json format and data type.", test.handleError(id));
  }

  @Test
  public void testGetErrorPath() {
    CustomErrorController test = new CustomErrorController();

    assertEquals(null, test.getErrorPath());
    System.out.println("passed CustomErrorController_getErrorPath");
  }
}
