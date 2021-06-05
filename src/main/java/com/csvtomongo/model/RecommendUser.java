package com.csvtomongo.model;

import lombok.Data;

@Data
public class RecommendUser extends Movies{

  private String link;
  private String poster;

  public String getLink() {
    return link;
  }

  public String getPoster() {
    return poster;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

}