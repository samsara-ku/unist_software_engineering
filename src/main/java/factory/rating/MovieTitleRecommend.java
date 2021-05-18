package factory.rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MovieTitleRecommend {
  private final RatingUtils ratingUtils;
  private final ArrayList<String> userList;
  private ArrayList<Integer> topRecommendMovies;
  private final int limit;
  private final String genre;
  private final String movieId;

  public MovieTitleRecommend(ArrayList<String> userList,int limit,String movieId,  String genre){
    this.ratingUtils = new RatingUtils();
    this.userList = userList;
    this.limit = limit;
    this.genre = genre;
    this.movieId = movieId;
  }


  public void recommendMovies(){
    HashMap<String, Integer>[] userIdIndexList =  ratingUtils.setUserIdIndexList();
    HashMap<String, String[]> movieGenreList = ratingUtils.getMovieGenreList();
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>();
    HashMap<Integer, Double> sumOfRating = new HashMap<>();
    HashMap<Integer, Integer> numOfRating = new HashMap<>();
    this.topRecommendMovies = new ArrayList<>();

    for(String userId : this.userList){
      int index = Integer.parseInt(userId) - 1;
      for(String movieIdString : userIdIndexList[index].keySet()){
        int weight = this.genreSimilarity(ratingUtils.parseGenre(this.genre) ,movieGenreList.get(movieIdString));
        int movieId = Integer.parseInt(movieIdString);
        if(movieId == Integer.parseInt(this.movieId))
          weight = 0;
        if(weight != 0){
          if( userIdIndexList[index].get(movieIdString) >= 3){
            if (numberOfMovie.get(movieId) == null) {
              numberOfMovie.put(movieId, weight);
            } else {
              numberOfMovie.put(movieId, numberOfMovie.get(movieId) + weight);
            }
          }
          if (sumOfRating.get(movieId) == null) {
            sumOfRating.put(movieId, Double.valueOf(userIdIndexList[index].get(movieIdString)));
            numOfRating.put(movieId, 1);
          } else {
            sumOfRating.put(movieId, sumOfRating.get(movieId) + userIdIndexList[index].get(movieIdString));
            numOfRating.put(movieId, numOfRating.get(movieId)+1);
          }
        }
      }
    }
    ArrayList<Integer> top30 = this.sortGetTop30(numberOfMovie);
    HashMap<Integer, Double> ratingList = new HashMap<>();
    for (Integer top30MovieId : top30){
      ratingList.put(top30MovieId, sumOfRating.get(top30MovieId)/numOfRating.get(top30MovieId));
    }

    List<Entry<Integer, Double>> sortedRating = sortWithRating(ratingList);

    int idx = 0;

    for (Entry<Integer, Double> entry : sortedRating) {
      if (idx < this.limit) {
        this.topRecommendMovies.add(entry.getKey());
        idx++;
      }
    }

  }

  public ArrayList<Integer> getTopRecommendMovies(){
    if(this.topRecommendMovies == null)
      recommendMovies();
    return this.topRecommendMovies;
  }

  public ArrayList<Integer> sortGetTop30(HashMap<Integer, Integer> numberOfMovie) {
    // Sorting number_list (descending order)
    List<Entry<Integer, Integer>> number_list = new ArrayList<>(
        numberOfMovie.entrySet());

    number_list.sort((obj1, obj2) -> obj2.getValue().compareTo(obj1.getValue()));

    // Top 30 most viewed movie
    ArrayList<Integer> top30 = new ArrayList<>();
    int ck = 1;

    for (Entry<Integer, Integer> entry : number_list) {
      if (ck <= limit*2) {
        top30.add(entry.getKey());
        ck = ck + 1;
      }
    }
    return top30;
  }

  public int genreSimilarity(String[] inputGenreList, String[] targetGenreList){
    int similarity = 0;

    for(String inputGenre : inputGenreList){
      for(String targetGenre : targetGenreList){
        if(inputGenre.equals(targetGenre)){
          if(similarity == 0)
            similarity = 1;
          else
            similarity *= 3;
        }
      }
    }
    return similarity;
  }

  public List<Entry<Integer, Double>> sortWithRating(HashMap<Integer, Double> avgRating) {
    // Sorting avgRating (descending order)
    List<Entry<Integer, Double>> sortedRating = new ArrayList<>(
        avgRating.entrySet());

    sortedRating.sort((obj1, obj2) -> obj2.getValue().compareTo(obj1.getValue()));

    return sortedRating;
  }

}
