package com.csvtomongo.model;

import lombok.Data;

@Data
public class Movies {

  private int id;
  private String title;
  private String genre;
  private String link;
  private String poster;
  private Double rating;

  public int getId() {
    return id;
  }

  public String getGenre() {
    return genre;
  }

  public String getTitle() {
    return title;
  }


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