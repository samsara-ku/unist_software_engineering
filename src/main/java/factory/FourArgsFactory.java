package factory;

import factory.link.LinkUserAndRating;
import factory.rating.RatingMoreThanTwoArgs;
import factory.user.LoadMoreThanTwoArgs;
import java.util.ArrayList;

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

  public boolean checkValidity(String[] input) {
    String gender = input[0];
    String age = input[1];
    String occupation = input[2];
    String categories = input[3];

    // Gender input error handling
    if (!(gender.equals("F") || (gender.equals("M"))
        || (gender.isEmpty()))) {
      System.out.println(
          "Wrong gender input. Please try again with F for female or M for male.");
      return false;
    }

    // Age input error handling
    if (!age.isEmpty()) {
      try {
        int age_parse = Integer.parseInt(age);
        if (age_parse <= 0) {
          System.out.println(
              "Wrong age input. (Non-positive age) Please try again with appropriate age.");
          return false;
        }
      } catch (Exception e) {
        System.out.println(
            "Wrong age input. (Not an integer) Please try again with appropriate age.");
        return false;
      }
    }

    boolean genrePass = categories.equals("");

    // Occupation input error handling
    boolean hasProperOccupation = new LoadMoreThanTwoArgs(gender, age, occupation)
        .hasContained(this.occupation_list, occupation);

    if (!(hasProperOccupation || occupation.isEmpty())) {
      System.out.printf(
          "Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n",
          occupation);
      return false;
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
      System.out
          .println(
              "Can't search because there is inappropriate category. Please try again with appropriate category.");
      return false;
    }
    return true;
  }

  public void getResult() {
    if (this.categories.equals("")) {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList());
    } else {
      this.resultMaker = new RatingMoreThanTwoArgs(this.userInfo.getUserList(), categories);
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
