package state;

import data.LoadLink;
import java.util.ArrayList;
import state.result.MoreThanTwoArgsFactory;
import state.user.LoadMoreThanTwoArgs;

public class FourArgs implements FactoryOutput {

  private final LoadMoreThanTwoArgs userInfo;
  private final String categories;
  private MoreThanTwoArgsFactory resultMaker;

  public FourArgs(String args1, String args2, String args3, String args4) {
    this.userInfo = new LoadMoreThanTwoArgs(args1, args2, args3);
    this.categories = args4;
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

    boolean genrePass = this.categories.equals("");

    // Occupation input error handling
    boolean hasProperOccupation = this.userInfo
        .hasContained(occupation_list, this.userInfo.getOccupation());

    if (!(hasProperOccupation || this.userInfo.getOccupation().isEmpty())) {
      System.out.printf(
          "Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n",
          this.userInfo.getOccupation());
      return;
    }

    // Genre input error handling
    String[] inputCategory = this.categories.split("\\|");

    int count = 0;

    for (String input : inputCategory) {
      for (String truthValue : category_list) {
        if (input.equalsIgnoreCase(truthValue)) {
          count++;
        }
      }
    }

    boolean valid_category = (inputCategory.length == count);

    if (!(genrePass || valid_category)) {
      System.out
          .println(
              "Can't search because there is inappropriate category. Please try again with appropriate category.");
      return;
    }

    if (genrePass) {
      this.resultMaker = new MoreThanTwoArgsFactory(this.userInfo.getUserList());
    } else {
      this.resultMaker = new MoreThanTwoArgsFactory(this.userInfo.getUserList(), this.categories);
    }

    LoadLink result = new LoadLink(this.resultMaker.getTop10());

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
