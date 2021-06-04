package com.csvtomongo.model;

import lombok.Data;

@Data
public class Posters {

  private int id;
  private String poster;

  public int getId() {
    return id;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }
}