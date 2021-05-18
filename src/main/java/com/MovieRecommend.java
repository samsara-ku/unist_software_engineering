package com;

public class MovieRecommend {

  private String title;
  private String limit;


  public MovieRecommend(String title, String limit) {
    this.title = title;
    this.limit = limit;
  }

  public String getTitle() {
    return this.title;
  }

  public String getLimit() {
    return this.limit;
  }

}