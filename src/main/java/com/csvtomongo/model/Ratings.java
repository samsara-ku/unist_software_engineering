package com.csvtomongo.model;

import lombok.Data;

@Data
public class Ratings {

  private int user;
  private int movie;
  private int rating;
  private int time;

}