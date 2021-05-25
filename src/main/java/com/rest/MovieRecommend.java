package com.rest;

import org.apache.commons.lang3.StringUtils;

public class MovieRecommend {

  private final String title;
  private final String limit;

  public MovieRecommend(String title, String limit) {
    this.title = title.isBlank() ? "" : title;
    this.limit = StringUtils.isBlank(limit) ? "10" : limit;
  }

  public String getTitle() {
    return this.title;
  }

  public String getLimit() {
    return this.limit;
  }
}