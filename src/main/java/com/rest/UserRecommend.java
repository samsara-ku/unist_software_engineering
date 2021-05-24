package com.rest;

public class UserRecommend {

  private String gender;
  private String age;
  private String occupation;
  private String genre;

  public UserRecommend(String gender, String age, String occupation, String genre) {
    this.setGender(gender);
    this.setAge(age);
    this.setOccupation(occupation);
    this.setGenre(genre);
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre.isBlank() ? "" : genre;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation.isBlank() ? "" : occupation;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age.isBlank() ? "" : age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender.isBlank() ? "" : gender;
  }
}
