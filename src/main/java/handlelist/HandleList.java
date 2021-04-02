package handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HandleList {

  private final ArrayList<String> movieList;
  private final ArrayList<String> userList;

  /* We use movie id and user id to filtering ratingList*/
  private ArrayList<String> movieIdList;
  private ArrayList<String> userIdList;
  private ArrayList<String> ratingList;

  public HandleList(ArrayList<String> movieList,
      ArrayList<String> userList) {
    this.movieList = movieList;
    this.userList = userList;
  }

  public double getAverageRating() {
    int movieIndexSize = getAllMovieListSize();
    int intMovieIdIndex;

    /* 모든 movieId를 포함할 수 있는 크기의 map Array 생성 */
    HashMap<String, String>[] movieIdIndexList = new HashMap[movieIndexSize];
    for (int i = 0; i < movieIndexSize; i++) {
      movieIdIndexList[i] = new HashMap<String, String>();
    }

    /* movieIdList, userIdList, ratingList 생성 */
    setRequiredLists();

    /* movieIdIndexList의 movieId에 해당하는 곳에 userid와 rating을 저장 */
    for (String rateLine : ratingList) {
      String[] parseLine = rateLine.split("::");
      intMovieIdIndex = Integer.parseInt(parseLine[1]) - 1;
      movieIdIndexList[intMovieIdIndex].put(parseLine[0], parseLine[2]);
    }

    int ratingCount = 0;
    double averageSum = 0;

        /* filterd된 movieId와 userId를 이용하여 해당 movieIndex의
            userId 포함 여부를 확인후 평균 계산 */
    for (String movieId : movieIdList) {
      intMovieIdIndex = Integer.parseInt(movieId) - 1;
      for (String userId : userIdList) {
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


  /* Set ArrayList that only has movieId */
  public void setMovieIdList() {
    this.movieIdList = new ArrayList<String>();
    for (String movie : this.movieList) {
      String temp = movie.split("::")[0];
      this.movieIdList.add(temp);
    }
  }

  /* Set ArrayList that only has userId */
  public void setUserIdList() {
    this.userIdList = new ArrayList<String>();
    for (String user : this.userList) {
      String temp = user.split("::")[0];
      this.userIdList.add(temp);
    }
  }

  /* Set RatingList from file */
  public void setRatingList() {
    this.ratingList = new ArrayList<String>();
    File file = new File("./data/ratings.dat");
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      while ((line = br.readLine()) != null) {
        ratingList.add(line);
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setRequiredLists() {
      if (movieIdList == null) {
          setMovieIdList();
      }
      if (userIdList == null) {
          setUserIdList();
      }
      if (ratingList == null) {
          setRatingList();
      }
  }

  /* get original movie list size from file */
  public int getAllMovieListSize() {
    File file = new File("./data/movies.dat");
    String line;
    String lastLine = null;
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) {
        lastLine = line;
      }
      br.close();
      return Integer.parseInt(lastLine.split("::")[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public ArrayList<String> getMovieList() {
    return movieList;
  }

  public ArrayList<String> getUserList() {
    return userList;
  }

  public ArrayList<String> getMovieIdList() {
      if (movieIdList == null) {
          setMovieIdList();
      }
    return movieIdList;
  }

  public ArrayList<String> getUserIdList() {
      if (userIdList == null) {
          setUserIdList();
      }
    return userIdList;
  }

  public ArrayList<String> getRatingList() {
      if (ratingList == null) {
          setRatingList();
      }
    return userIdList;
  }

}
