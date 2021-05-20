package com.rest;

public class MovieRecommend {

  private final String title;
  private final String limit;

  public MovieRecommend(String title, String limit) {
    this.title = title == null ? "" : title;
    this.limit = limit == null ? "10" : limit;
  }

  public String getTitle() {
    return this.title;
  }

  public String getLimit() {
    return this.limit;
  }
}