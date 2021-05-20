package com.rest;

public class MovieRecommend {

  private final String title;
  private final Integer limit;

  public MovieRecommend(String title, Integer limit) {
    this.title = title == null ? "" : title;
    this.limit = limit == null ? Integer.valueOf(10) : limit;
  }

  public String getTitle() {
    return this.title;
  }

  public int getLimit() {
    return this.limit;
  }
}