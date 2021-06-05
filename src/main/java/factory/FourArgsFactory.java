package factory;

import factory.link.LinkUserAndRating;
import factory.rating.RatingMoreThanTwoArgs;
import factory.user.LoadMoreThanTwoArgs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class FourArgsFactory extends Factory {

  private LoadMoreThanTwoArgs userInfo;
  private String categories;
  private RatingMoreThanTwoArgs resultMaker;

  public FourArgsFactory() {
  }

  public FourArgsFactory(String args1, String args2, String args3, String args4) {
    this.userInfo = new LoadMoreThanTwoArgs(args1, args2, args3);
    this.categories = args4;
  }

  public String checkValidity(String[] input) {
    String gender = input[0];
    String age = input[1];
    String occupation = input[2];
    String categories = input[3];

    // Gender input error handling
    if (!(gender.equalsIgnoreCase("F") || (gender.equalsIgnoreCase("M"))
        || (gender.isEmpty()))) {
      return "Wrong gender input. Please try again with F for female or M for male.";
    }

    // Age input error handling
    if (!age.isEmpty()) {
      try {
        int age_parse = Integer.parseInt(age);
        if (age_parse <= 0) {
          return "Wrong age input. (Non-positive age) Please try again with appropriate age.";
        }
      } catch (Exception e) {
        return "Wrong age input. (Not an integer) Please try again with appropriate age.";
      }
    }

    boolean genrePass = categories.isBlank();

    // Occupation input error handling
    boolean hasProperOccupation = new LoadMoreThanTwoArgs(gender, age, occupation)
        .hasContained(this.occupation_list, occupation);

    if (!(hasProperOccupation || occupation.isEmpty())) {
      return String.format(
          "Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n",
          occupation);
    }

    // Genre input error handling
    String[] inputCategory = categories.split("\\|");
    int count = 0;

    for (String element : inputCategory) {
      for (String truthValue : category_list) {
        if (element.equalsIgnoreCase(truthValue)) {
          count++;
        }
      }
    }

    boolean valid_category = (inputCategory.length == count);

    if (!(genrePass || valid_category)) {
      return "Can't search because there is inappropriate category. Please try again with appropriate category.";
    }

    return "";
  }

  public ArrayList<String> getResult() {
    if (this.categories.isBlank()) {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList());
    } else {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList(), this.categories);
    }

    LinkUserAndRating result = new LinkUserAndRating(this.resultMaker.getTop10(), 10);

    return result.getLinkList();
  }

  public List<Entry<Integer, Double>> getTop10() {
    if (this.categories.isBlank()) {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList());
    } else {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList(), this.categories);
    }

    return this.resultMaker.getTop10WithRating();
  }

  public void printResult() {
    if (this.categories.isBlank()) {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList());
    } else {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList(), this.categories);
    }

    LinkUserAndRating result = new LinkUserAndRating(this.resultMaker.getTop10(), 10);

    ArrayList<String> top10 = result.getLinkList();

    int idx = 0;

    for (String line : top10) {
      String name = line.split("/")[0];
      String link = line.split("/")[1];
      int number = this.resultMaker.getTop10_num().get(idx);
      double rating = this.resultMaker.getTop10_rat().get(idx);
      idx = idx + 1;
      System.out.printf(
          "%d. %s (https://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.%n",
          idx, name, link, number, rating);
    }
  }
}
