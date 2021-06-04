package com.csvtomongo.model;

import lombok.Data;

@Data
public class Links {

  private int id;
  private String link;

  public int getId() {
    return id;
  }

  public String getLink() {
    return link;
  }
}
