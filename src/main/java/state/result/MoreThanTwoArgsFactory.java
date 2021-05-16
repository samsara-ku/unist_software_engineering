package state.result;

import handlelist.HandleList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MoreThanTwoArgsFactory {

  final HandleList handleList;
  private final ArrayList<Integer> top10 = new ArrayList<Integer>();
  private final ArrayList<Integer> top10_num = new ArrayList<Integer>();
  private final ArrayList<Double> top10_rat = new ArrayList<Double>();
  private final HashMap<String, Double> userList;
  private HashMap<String, Integer>[] userIdIndexList;
  private double sumOfWeight = 0;
  private int numOfWeight = 0;
  private String genre = "";

  public MoreThanTwoArgsFactory(HashMap<String, Double> userList) {
    this.handleList = new HandleList();
    this.userList = userList;
  }

  public MoreThanTwoArgsFactory(HashMap<String, Double> userList, String genre) {
    this.handleList = new HandleList();
    this.userList = userList;
    this.genre = genre;
  }

  public HashMap<Integer, Integer> numberOfMovie(HashMap<String, Double> userList) {
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>(
        handleList.getFileListSize("./data/movies.dat"));

    for (HashMap.Entry<String, Double> user : userList.entrySet()) {
      int index = Integer.parseInt(user.getKey()) - 1;
      for (HashMap.Entry<String, Integer> entry : this.userIdIndexList[index].entrySet()) {
        int movieID = Integer.parseInt(entry.getKey());
        if (numberOfMovie.get(movieID) == null) {
          numberOfMovie.put(movieID, 1);
        } else {
          numberOfMovie.put(movieID, numberOfMovie.get(movieID) + 1);
        }
      }
    }
    return numberOfMovie;
  }

  public HashMap<Integer, Double> sumOfRating(HashMap<String, Double> userList) {
    HashMap<Integer, Double> sumOfRating = new HashMap<>(
        handleList.getFileListSize("./data/movies.dat"));

    for (HashMap.Entry<String, Double> user : userList.entrySet()) {
      int index = Integer.parseInt(user.getKey()) - 1;

      for (HashMap.Entry<String, Integer> entry : this.userIdIndexList[index].entrySet()) {
        int movieID = Integer.parseInt(entry.getKey());
        int rating = entry.getValue();

        if (sumOfRating.get(movieID) == null) {
          double sum = rating * user.getValue();
          this.sumOfWeight += user.getValue();
          this.numOfWeight++;
          sumOfRating.put(movieID, sum);
        } else {
          double sum = sumOfRating.get(movieID) + rating * user.getValue();
          this.sumOfWeight += user.getValue();
          this.numOfWeight++;
          sumOfRating.put(movieID, sum);
        }
      }
    }
    return sumOfRating;
  }

  public ArrayList<Integer> sortGetTop30(HashMap<Integer, Integer> numberOfMovie) {
    // Sorting number_list (descending order)
    List<Entry<Integer, Integer>> number_list = new ArrayList<Entry<Integer, Integer>>(
        numberOfMovie.entrySet());

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
    return top30;
  }

  public List<Entry<Integer, Double>> sortWithRating(HashMap<Integer, Double> avgRating) {
    // Sorting avgRating (descending order)
    List<Entry<Integer, Double>> sortedRating = new ArrayList<Entry<Integer, Double>>(
        avgRating.entrySet());

    Collections.sort(sortedRating, new Comparator<Entry<Integer, Double>>() {
      public int compare(Entry<Integer, Double> obj1, Entry<Integer, Double> obj2) {
        return obj2.getValue().compareTo(obj1.getValue());
      }
    });

    return sortedRating;
  }

  public ArrayList<Integer> getTop10() {
    setTop10(this.userList);
    return this.top10;
  }

  // Find the best 10 movies to recommend
  public void setTop10(HashMap<String, Double> userList) {
    this.userIdIndexList = this.genre.isEmpty() ? handleList.setUserIdIndexList()
        : handleList.setUserIdIndexList(handleList.parseGenre(this.genre));

    // Create MovieList about number of rating and sum of rating
    HashMap<Integer, Integer> numberOfMovie = numberOfMovie(userList);
    HashMap<Integer, Double> sumOfRating = sumOfRating(userList);

    // Get Top30 watched movie list
    ArrayList<Integer> top30 = sortGetTop30(numberOfMovie);

    HashMap<Integer, Double> avgRating = new HashMap<>(30);

    // Calculate average rating
    for (int movieID : top30) {
      double sum = sumOfRating.get(movieID);
      int num = numberOfMovie.get(movieID);
      double avg_rating = sum / (double) num;
      double avg_weight = this.sumOfWeight / (double) this.numOfWeight;

      avgRating.put(movieID, avg_rating / avg_weight);
    }

    // Sorting avgRating (descending order)
    List<Entry<Integer, Double>> sortedRating = sortWithRating(avgRating);

    // Top 10 highest rated movie in Top 30 most viewed movie set
    int idx = 1;

    for (Entry<Integer, Double> entry : sortedRating) {
      if (idx <= 10) {
        this.top10.add(entry.getKey());
        this.top10_rat.add(entry.getValue());
        this.top10_num.add(numberOfMovie.get(entry.getKey()));
        idx = idx + 1;
      }
    }
  }

  public ArrayList<Integer> getTop10_num() {
    return this.top10_num;
  }

  public ArrayList<Double> getTop10_rat() {
    return this.top10_rat;
  }

  public HashMap<String, Integer>[] getUserIdIndexList() {
    return this.userIdIndexList;
  }
}

