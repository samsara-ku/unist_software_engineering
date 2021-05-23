package com.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class MovieRecommendTest {

  @Test
  public void allTest() {
    MovieRecommend actualInput = new MovieRecommend("test_title", "3");

    assertEquals("test_title", actualInput.getTitle());
    System.out.println("passed MovieRecommend_title");

    assertEquals("3", actualInput.getLimit());
    System.out.println("passed MovieRecommend_litle");
  }

  @Test
  public void getTitleTest() {
    MovieRecommend movieRecommend = new MovieRecommend("test_title", "3");

    assertNotNull("Title is NULL", movieRecommend.getTitle());
    System.out.println("passed MovieRecommend_getTitle");
  }

  @Test
  public void getLimit() {
    MovieRecommend movieRecommend = new MovieRecommend("test_title", "3");

    assertNotNull("Limit is NULL", movieRecommend.getLimit());
    System.out.println("passed MovieRecommend_getLimit");
  }
}
