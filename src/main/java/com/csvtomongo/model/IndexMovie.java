package com.csvtomongo.model;

import lombok.Data;

@Data
public class IndexMovie extends Movies{

  private String link;
  private String poster;
  private Double rating;

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

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }
}