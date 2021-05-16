package state.result;

import handlelist.HandleList;
import java.util.ArrayList;
import java.util.HashMap;

public class TwoArgsFactory {

  private final HandleList handleList;
  private final ArrayList<String> movieList;
  private final ArrayList<String> userList;

  /* We use movie id and user id to filtering ratingList*/
  private ArrayList<String> movieIdList;
  private ArrayList<String> userIdList;

  public TwoArgsFactory(ArrayList<String> movieList, ArrayList<String> userList) {
    this.handleList = new HandleList();
    this.movieList = movieList;
    this.userList = userList;
  }

  public double getAverageRating() {
    int intMovieIdIndex = 0;
    int ratingCount = 0;
    double averageSum = 0;
    HashMap<String, String>[] movieIdIndexList = handleList.getMovieIdIndexList();

    /* movieIdList, userIdList 생성*/
    setIdLists();

    /* filtered된 movieId와 userId를 이용하여 해당 movieIndex의 userId 포함 여부를 확인후 평균 계산 */
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
