package com.csvtomongo.model;

import lombok.Data;

@Data
public class Movies {

  private int id;
  private String title;
  private String genre;

  public int getId() {
    return id;
  }

  public String getGenre() {
    return genre;
  }

  public String getTitle() {
    return title;
  }
}