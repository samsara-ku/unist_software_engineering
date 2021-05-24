package com.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class MovieRecommendTest {

  @Test
  public void allTest() {
    MovieRecommend actualInput = new MovieRecommend("", "");

    assertEquals("", actualInput.getTitle());
    System.out.println("passed MovieRecommend_title");

    assertEquals("10", actualInput.getLimit());
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
