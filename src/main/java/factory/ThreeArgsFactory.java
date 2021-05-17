package factory;

import factory.link.LinkUserAndRating;
import java.util.ArrayList;
import factory.rating.RatingMoreThanTwoArgs;
import factory.user.LoadMoreThanTwoArgs;

public class ThreeArgsFactory extends Factory {

  private final LoadMoreThanTwoArgs userInfo;
  private final RatingMoreThanTwoArgs resultMaker;

  public ThreeArgsFactory(String args1, String args2, String args3) {
    this.userInfo = new LoadMoreThanTwoArgs(args1, args2, args3);
    this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList());
  }

  public void getResult() {
    // Gender input error handling
    if (!(this.userInfo.getGender().equals("F") || (this.userInfo.getGender().equals("M"))
        || (this.userInfo.getGender().isEmpty()))) {
      System.out.println(
          "Wrong gender input. Please try again with F for female or M for male.");
      return;
    }

    // Age input error handling
    if (!this.userInfo.getAge().isEmpty()) {
      try {
        int age = Integer.parseInt(this.userInfo.getAge());
        if (age <= 0) {
          System.out.println(
              "Wrong age input. (Non-positive age) Please try again with appropriate age.");
          return;
        }
      } catch (Exception e) {
        System.out.println(
            "Wrong age input. (Not an integer) Please try again with appropriate age.");
        return;
      }
    }

    boolean hasProperOccupation = this.userInfo
        .hasContained(occupation_list, this.userInfo.getOccupation());

    if (!(hasProperOccupation || this.userInfo.getOccupation().isEmpty())) {
      System.out.printf(
          "Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n",
          this.userInfo.getOccupation());
      return;
    }

    LinkUserAndRating result = new LinkUserAndRating(this.resultMaker.getTop10());

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
