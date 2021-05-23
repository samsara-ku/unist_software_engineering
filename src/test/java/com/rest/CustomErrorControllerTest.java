package com.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CustomErrorControllerTest {

  @Test
  public void testHandleError() {
    CustomErrorController test = new CustomErrorController;

    assertEqauls("Please input the appropriate json format and data type.", test.handleError);
  }

  @Test
  public void testGetErrorPath() {
    CustomErrorController test = new CustomErrorController;

    assertEquals(null, test.getErrorPath());
    System.out.println("passed CustomErrorController_getErrorPath");
  }
}
