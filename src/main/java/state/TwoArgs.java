package state;

import java.util.ArrayList;
import java.util.Arrays;
import state.result.TwoArgsFactory;
import state.user.LoadTwoArgs;

public class TwoArgs {

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

  private final LoadTwoArgs userInfo;
  private final TwoArgsFactory resultMaker;

  public TwoArgs(String args1, String args2) {
    this.userInfo = new LoadTwoArgs(args1, args2);
    this.resultMaker = new TwoArgsFactory(this.userInfo.getMovieList(),
        this.userInfo.getUserList());
  }

  public void getResult() {
    int movieCount = this.userInfo.getMovieList().size();
    double result = this.resultMaker.getAverageRating();
    boolean hasProperCategories = this.userInfo
        .hasContained(category_list, this.userInfo.getCategories());
    boolean hasProperOccupation = this.userInfo
        .hasContained(occupation_list, this.userInfo.getOccupation());

    if (!hasProperCategories && !hasProperOccupation) {
      System.out
          .println(String.format(
              "Can't search because there are inappropriate category and occupation. Please try again with appropriate category and occupation.",
              this.userInfo.getOccupation()));
      return;
    } else if (!hasProperCategories) {
      System.out
          .println(
              "Can't search because there is inappropriate category. Please try again with appropriate category.");
      return;
    } else if (!hasProperOccupation) {
      System.out.println(String.format(
          "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.",
          this.userInfo.getOccupation()));
      return;
    } else {
      if (movieCount == 0) {
        System.out.println(
            "Despite the correct category and occupation, nothing was found. Please try again with different category.");
        return;
      }

      if (result == 0) {
        System.out.println(String.format(
            "There are a total of \"%d\" movies that fit the requested category, but the average score is 0 points due to no rating data. Please try again with another occupation.",
            movieCount));
        return;
      }

      // Result for searching and calculating avg. score
      System.out.println(String.format(
          "There are a total of \"%d\" movies that fit the requested category, and the average score is about \"%.2f\" points.",
          movieCount, result));
    }
  }
}
