package factory.rating;

import java.util.ArrayList;
import java.util.HashMap;

public class RatingWithTwoArgs {

  private final RatingUtils ratingUtils;
  private final ArrayList<String> movieList;
  private final ArrayList<String> userList;

  /* We use movie id and user id to filtering ratingList */
  private ArrayList<String> movieIdList;
  private ArrayList<String> userIdList;

  public RatingWithTwoArgs(ArrayList<String> movieList, ArrayList<String> userList) {
    this.ratingUtils = new RatingUtils();
    this.movieList = movieList;
    this.userList = userList;
  }

  public double getAverageRating() {
    int intMovieIdIndex;
    int ratingCount = 0;
    double averageSum = 0;
    HashMap<String, String>[] movieIdIndexList = ratingUtils.getMovieIdIndexList();

    /* Generate movieIdList, userIdList */
    setIdLists();

    /* Using filtered movieId and userId, make avg. score with checking userId corresponding movieIndex */
    for (String movieId : this.movieIdList) {
      intMovieIdIndex = Integer.parseInt(movieId) - 1;

      for (String userId : this.userIdList) {
        if (movieIdIndexList[intMovieIdIndex].containsKey(userId)) {
          averageSum += Integer.parseInt(movieIdIndexList[intMovieIdIndex].get(userId));
          ratingCount++;
        }
      }
    }

    if (ratingCount == 0) {
      return 0;
    }
    averageSum /= ratingCount;
    return averageSum;
  }

  public void setIdLists() {
    if (movieIdList == null) {
      this.movieIdList = ratingUtils.getIdList(this.movieList);
    }
    if (userIdList == null) {
      this.userIdList = ratingUtils.getIdList(this.userList);
    }
  }

  public ArrayList<String> getMovieIdList() {
    if (movieIdList == null) {
      this.movieIdList = ratingUtils.getIdList(this.movieList);
    }
    return movieIdList;
  }

  public ArrayList<String> getUserIdList() {
    if (userIdList == null) {
      this.userIdList = ratingUtils.getIdList(this.userList);
    }
    return userIdList;
  }
}
