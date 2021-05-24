package com.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserRecommendTest {

  @Test
  public void userRecommendTest() {
    UserRecommend actualInput = new UserRecommend("F", "32", "unemployed", "comedy|action");

    assertNotNull("Genre is NULL", actualInput.getGenre());
    System.out.println("passed UserRecommend_setGenre");

    assertNotNull("Occupation is NULL", actualInput.getOccupation());
    System.out.println("passed UserRecommend_setOccupation");

    assertNotNull("Age is NULL", actualInput.getAge());
    System.out.println("passed UserRecommend_setAge");

    assertNotNull("Gender is NULL", actualInput.getGender());
    System.out.println("passed UserRecommend_setGender");

    System.out.println("passed userRecommendTest");
  }

  @Test
  public void testGet() {
    UserRecommend actualInput = new UserRecommend("F", "32", "unemployed", "comedy|action");

    assertEquals("F", actualInput.getGender());
    System.out.println("passed UserRecommend_getGender");

    assertEquals("32", actualInput.getAge());
    System.out.println("passed UserRecommend_getAge");

    assertEquals("unemployed", actualInput.getOccupation());
    System.out.println("passed UserRecommend_getOccupation");

    assertEquals("comedy|action", actualInput.getGenre());
    System.out.println("passed UserRecommend_getGenre");

    System.out.println("passed testGet");
  }
}
