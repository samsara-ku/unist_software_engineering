package factory;

import factory.link.LinkUserAndRating;
import factory.rating.RatingMoreThanTwoArgs;
import factory.user.LoadMoreThanTwoArgs;
import java.util.ArrayList;

public class ThreeArgsFactory extends Factory {

  private LoadMoreThanTwoArgs userInfo;
  private RatingMoreThanTwoArgs resultMaker;

  public ThreeArgsFactory() {
  }

  public ThreeArgsFactory(String args1, String args2, String args3) {
    this.userInfo = new LoadMoreThanTwoArgs(args1, args2, args3);
    this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList());
  }

  public String checkValidity(String[] input) {
    String gender = input[0];
    String age = input[1];
    String occupation = input[2];

    // Gender input error handling
    if (!(gender.equals("F") || (gender.equals("M"))
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

    boolean hasProperOccupation = new LoadMoreThanTwoArgs(gender, age, occupation)
        .hasContained(occupation_list, occupation);

    if (!(hasProperOccupation || occupation.isEmpty())) {
      return String.format(
          "Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n",
          occupation);
    }

    return "";
  }

  public void printResult() {
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
          "%d. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.%n",
          idx, name, link, number, rating);
    }
  }
}
