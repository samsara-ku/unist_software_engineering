package factory.rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class RatingMoreThanTwoArgs extends RecommendMovieAbstract {

  private final RatingUtils ratingUtils;
  private final ArrayList<Integer> top10 = new ArrayList<>();
  private final ArrayList<Integer> top10_num = new ArrayList<>();
  private final ArrayList<Double> top10_rat = new ArrayList<>();
  private final List<Entry<Integer,Double>> top10WithRating = new ArrayList<>();
  private final HashMap<String, Double> userList;
  private HashMap<String, Integer>[] userIdIndexList;
  private double sumOfWeight = 0;
  private int numOfWeight = 0;
  private String genre = "";

  public RatingMoreThanTwoArgs(HashMap<String, Double> userList) {
    this.ratingUtils = new RatingUtils();
    this.userList = userList;
  }

  public RatingMoreThanTwoArgs(HashMap<String, Double> userList, String genre) {
    this.ratingUtils = new RatingUtils();
    this.userList = userList;
    this.genre = genre;
  }

  public HashMap<Integer, Integer> numberOfMovie() {
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>(
        ratingUtils.getFileListSize("./data/movies.dat"));

    for (HashMap.Entry<String, Double> user : this.userList.entrySet()) {
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

  public HashMap<Integer, Double> sumOfRating() {
    HashMap<Integer, Double> sumOfRating = new HashMap<>(
        ratingUtils.getFileListSize("./data/movies.dat"));

    for (HashMap.Entry<String, Double> user : this.userList.entrySet()) {
      int index = Integer.parseInt(user.getKey()) - 1;

      for (HashMap.Entry<String, Integer> entry : this.userIdIndexList[index].entrySet()) {
        int movieID = Integer.parseInt(entry.getKey());
        int rating = entry.getValue();
        double sum = sumOfRating.get(movieID) == null ? rating * user.getValue()
            : sumOfRating.get(movieID) + rating * user.getValue();
        this.sumOfWeight += user.getValue();
        this.numOfWeight += 1;
        sumOfRating.put(movieID, sum);
      }
    }
    return sumOfRating;
  }


  public List<Entry<Integer, Double>> getTop10() {
    setTop10(this.userList);
    return this.top10WithRating;
  }

  // Find the best 10 movies to recommend
  public void setTop10(HashMap<String, Double> userList) {
    this.userIdIndexList = this.genre.isEmpty() ? ratingUtils.setUserIdIndexList()
        : ratingUtils.setUserIdIndexList(ratingUtils.parseGenre(this.genre));

    // Create MovieList about number of rating and sum of rating
    HashMap<Integer, Integer> numberOfMovie = numberOfMovie();
    HashMap<Integer, Double> sumOfRating = sumOfRating();

    // Get Top30 watched movie list
    ArrayList<Integer> top30 = sortGetTopN(numberOfMovie, 30);

    HashMap<Integer, Double> avgRating = new HashMap<>(30);

    // Calculate average rating
    for (int movieID : top30) {
      double avg_rating = sumOfRating.get(movieID) / (double) numberOfMovie.get(movieID);
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
        this.top10WithRating.add(entry);
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

