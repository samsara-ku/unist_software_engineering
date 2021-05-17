package state.result;

import handlelist.HandleList;
import java.util.ArrayList;
import java.util.HashMap;

public class TwoArgsFactory {

  private final HandleList handleList;
  private final ArrayList<String> movieList;
  private final ArrayList<String> userList;

  /* We use movie id and user id to filtering ratingList */
  private ArrayList<String> movieIdList;
  private ArrayList<String> userIdList;

  public TwoArgsFactory(ArrayList<String> movieList, ArrayList<String> userList) {
    this.handleList = new HandleList();
    this.movieList = movieList;
    this.userList = userList;
  }

  public double getAverageRating() {
    int intMovieIdIndex;
    int ratingCount = 0;
    double averageSum = 0;
    HashMap<String, String>[] movieIdIndexList = handleList.getMovieIdIndexList();

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
      this.movieIdList = handleList.getIdList(this.movieList);
    }
    if (userIdList == null) {
      this.userIdList = handleList.getIdList(this.userList);
    }
  }

  public ArrayList<String> getMovieIdList() {
    if (movieIdList == null) {
      this.movieIdList = handleList.getIdList(this.movieList);
    }
    return movieIdList;
  }

  public ArrayList<String> getUserIdList() {
    if (userIdList == null) {
      this.userIdList = handleList.getIdList(this.userList);
    }
    return userIdList;
  }
}
