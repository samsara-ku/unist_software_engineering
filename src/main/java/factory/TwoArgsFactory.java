package factory;

import factory.rating.RatingWithTwoArgs;
import factory.user.LoadTwoArgs;

public class TwoArgsFactory extends Factory {

  private LoadTwoArgs userInfo;
  private RatingWithTwoArgs resultMaker;

  public TwoArgsFactory() {
  }

  public TwoArgsFactory(String args1, String args2) {
    this.userInfo = new LoadTwoArgs(args1, args2);
    this.resultMaker = new RatingWithTwoArgs(this.userInfo.getMovieList(),
        this.userInfo.getUserList());
  }

  public boolean checkValidity(String[] input) {
    return true;
  }

  public void getResult() {
    int movieCount = this.userInfo.getMovieList().size();
    double rating = this.resultMaker.getAverageRating();
    boolean hasProperCategories = this.userInfo
        .hasContained(category_list, this.userInfo.getCategories());
    boolean hasProperOccupation = this.userInfo
        .hasContained(occupation_list, this.userInfo.getOccupation());

    if (!hasProperCategories && !hasProperOccupation) {
      System.out.println(
          "Can't search because there are inappropriate category and occupation. Please try again with appropriate category and occupation.");
      return;
    } else if (!hasProperCategories) {
      System.out
          .printf(
              "Can't search inappropriate category, \"%s\". Please try again with appropriate category.%n",
              this.userInfo.getCategories());
      return;
    } else if (!hasProperOccupation) {
      System.out.printf(
          "Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n",
          this.userInfo.getOccupation());
      return;
    } else {
      if (movieCount == 0) {
        System.out.println(
            "Despite the correct category and occupation, nothing was found. Please try again with different category.");
        return;
      } else if (rating == 0) {
        System.out.printf(
            "There are a total of \"%d\" movies that fit the requested category, but the average score is 0 points due to no rating data. Please try again with another occupation.%n",
            movieCount);
        return;
      }

      System.out.printf(
          "There are a total of \"%d\" movies that fit the requested category, and the average score is about \"%.2f\" points.%n",
          movieCount, rating);
    }
  }
}
