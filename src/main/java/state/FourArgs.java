package state;

import data.LoadLink;
import handlelist.SimilarUserRecommend;
import java.util.ArrayList;
import java.util.Arrays;
import state.result.MoreThanTwoArgsFactory;
import state.user.LoadMoreThanTwoArgs;

public class FourArgs {

  private final ArrayList<String> occupation_list = new ArrayList<>(Arrays.asList(
      "Academic", "Educator", "Artist", "Clerical",
      "Admin", "College", "Gradstudent", "Custormerservice", "Doctor", "Healthcare", "Executive",
      "Managerial", "Farmer", "Homemaker", "K-12student", "Lawyer", "Programmer", "Retired",
      "Sales", "Marketing", "Scientist", "Self-employed", "Technician", "Engineer", "Tradesman",
      "Craftsman", "Unemployed", "Writer"));

  private final ArrayList<String> category_list = new ArrayList<>(Arrays.asList(
      "Action", "Adventure", "Animation", "Children's",
      "Comedy", "Crime", "Documentary", "Drama", "Fantasy", "Film-noir", "Horror", "Musical",
      "Mystery", "Romance", "Sci-fi", "Thriller", "War", "Western"));

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
      System.out.println(String.format(
          "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.",
          this.userInfo.getOccupation()));
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

    SimilarUserRecommend best;
    if (genrePass == true) {
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
      System.out.println(String
          .format("%d. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.",
              idx, name, link, number, rating));
    }
  }
}
