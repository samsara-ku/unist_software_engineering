package handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AverageRating {
  private final ArrayList<String> movieList;
  private final ArrayList<String> userList;

  /* We use movie id and user id to filtering ratingList*/
  private ArrayList<String> movieIdList;
  private ArrayList<String> userIdList;
  private ArrayList<String> ratingList;

  public AverageRating(ArrayList<String> movieList,
      ArrayList<String> userList) {
    this.movieList = movieList;
    this.userList = userList;
  }

  public double getAverageRating() {
    int movieIndexSize = getAllMovieListSize();
    int intMovieIdIndex;
    int ratingCount = 0;
    double averageSum = 0;
    HashMap<String, String>[] movieIdIndexList = setMoiveIdIndexList();


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

  public HashMap<String, String>[] setMoiveIdIndexList(){
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
    return movieIdIndexList;
  }


  /* Set ArrayList that only has movieId */
  public ArrayList<String> setIdList(ArrayList<String> list) {
    ArrayList<String> newIdList = new ArrayList<String>();
    for (String listLine : list) {
      String temp = listLine.split("::")[0];
      newIdList.add(temp);
    }
    return newIdList;
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
      this.movieIdList = setIdList(this.movieList);
    }
    if (userIdList == null) {
      this.userIdList = setIdList(this.userList);
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
      this.movieIdList = setIdList(this.movieList);
    }
    return movieIdList;
  }

  public ArrayList<String> getUserIdList() {
    if (userIdList == null) {
      this.userIdList = setIdList(this.userList);
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
