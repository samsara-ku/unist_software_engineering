package com;

public class MovieRecommend {

  private String title;

  private Integer limit;

  public MovieRecommend(String title, Integer limit) {
    if(title == null)
      this.title = "";
    else
      this.title = title;
    if(limit == null)
      this.limit = 10;
    else
      this.limit = limit;
  }



  public String getTitle() {
    return this.title;
  }

  public int getLimit() {
    return this.limit;
  }

}