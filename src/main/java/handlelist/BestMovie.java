package handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BestMovie {

  private ArrayList<String> movieList = new ArrayList<String>();
  private ArrayList<Integer> top10 = new ArrayList<Integer>();
  private ArrayList<Integer> top10_num = new ArrayList<Integer>();
  private ArrayList<Double> top10_rat = new ArrayList<Double>();
  private  HashMap<String, Integer>[] userIdIndexList;


  public BestMovie(ArrayList<String> userList) {

    this.setUserIdIndexList(userList);
    this.setTop10(userList);


  }

  public boolean hasContained(String user, ArrayList<String> userList) {
      for (String listedUser : userList) {
          if (listedUser.equals(user)) {
              return true;
          }
      }
      return false;
  }

  public ArrayList<Integer> getTop10_num() {
    return this.top10_num;
  }

  public ArrayList<Double> getTop10_rat() {
    return this.top10_rat;
  }

  public ArrayList<String> getMovieList() {
    return this.movieList;
  }

  public void setUserIdIndexList(ArrayList<String> UserList) {

    int userSize = getAllUserListSize();

    userIdIndexList = new HashMap[userSize];
    for (int i = 0; i < userSize; i++) {
      userIdIndexList[i] = new HashMap<String, Integer>();
    }

    /* movieIdIndexList의 movieId에 해당하는 곳에 userid와 rating을 저장 */
    int intUserIdIndex;

    File file = new File("./data/ratings.dat");

    try {

      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {

        String[] parseLine = line.split("::");
        intUserIdIndex = Integer.parseInt(parseLine[0]) - 1;
        userIdIndexList[intUserIdIndex].put(parseLine[1], Integer.parseInt(parseLine[2]));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
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

  public int getAllUserListSize() {
    File file = new File("./data/users.dat");
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

  public ArrayList<Integer> getTop10() {
    return this.top10;
  }

  // Find the best 10 movies to recommend 

  public void setTop10(ArrayList<String> userList) {

    int userSize = getAllUserListSize();

    int movieIndexSize = getAllMovieListSize();
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>(movieIndexSize);
    HashMap<Integer, Integer> sumOfRating = new HashMap<>(movieIndexSize);

    // Count the number of movie watched by users, and calculate rating

    for (String userId : userList) {
      int index = Integer.parseInt(userId) - 1;
      for (HashMap.Entry<String, Integer> entry : userIdIndexList[index].entrySet()) {

        int movieID = Integer.parseInt(entry.getKey());
        int rating = entry.getValue();
        if (numberOfMovie.get(movieID) == null) {
          int num = 1;
          numberOfMovie.put(movieID, num);
        } else {
          int num = numberOfMovie.get(movieID) + 1;
          numberOfMovie.put(movieID, num);
        }

        if (sumOfRating.get(movieID) == null) {
          int sum = rating;
          sumOfRating.put(movieID, sum);
        } else {
          int sum = sumOfRating.get(movieID) + rating;
          sumOfRating.put(movieID, sum);
        }
      }
    }


    // Sorting number_list (descending order)
    List<Entry<Integer, Integer>> number_list = new ArrayList<Entry<Integer, Integer>>(numberOfMovie.entrySet());

    Collections.sort(number_list, new Comparator<Entry<Integer, Integer>>() {
      public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
        return obj2.getValue().compareTo(obj1.getValue());
      }
    });

    // Top 30 most viewed movie
    ArrayList<Integer> top30 = new ArrayList<Integer>();
    int ck = 1;

    for (Entry<Integer, Integer> entry : number_list) {
      if (ck <= 30) {
        top30.add(entry.getKey());
        ck = ck + 1;
      }
    }

    HashMap<Integer, Double> avgRating = new HashMap<>(30);

    // Calculate average rating
    for (int movieID : top30) {
      int sum = sumOfRating.get(movieID);
      int num = numberOfMovie.get(movieID);
      double avg_rating = (double)sum / (double)num;
      avgRating.put(movieID, avg_rating);
    }

    // Sorting avgRating (descending order)
    List<Entry<Integer, Double>> sortedRating = new ArrayList<Entry<Integer, Double>>(avgRating.entrySet());

    Collections.sort(sortedRating, new Comparator<Entry<Integer, Double>>() {
      public int compare(Entry<Integer, Double> obj1, Entry<Integer, Double> obj2) {
        return obj2.getValue().compareTo(obj1.getValue());
      }
    });

    // Top 10 highest rated movie in Top 30 most viewed movie set
    int idx = 1;

    for (Entry<Integer, Double> entry : sortedRating) {
      if (idx <= 10) {
        this.getTop10().add(entry.getKey());
        this.getTop10_rat().add(entry.getValue());
        this.getTop10_num().add(numberOfMovie.get(entry.getKey()));
        idx = idx + 1;
      }
    }
  }
}