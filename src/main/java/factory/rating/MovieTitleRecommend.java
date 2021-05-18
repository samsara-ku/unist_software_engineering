package factory.rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MovieTitleRecommend extends RecommendMovieAbstract{
  private final RatingUtils ratingUtils;
  private final ArrayList<String> userList;
  private ArrayList<Integer> topRecommendMovies;
  private HashMap<String, Integer>[] userIdIndexList;
  private HashMap<String, String[]> movieGenreList;
  private HashMap<Integer, Integer> numOfRating;
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
    this.userIdIndexList =  ratingUtils.setUserIdIndexList();
    this.movieGenreList = ratingUtils.getMovieGenreList();

    HashMap<Integer, Integer> numberOfMovie = numberOfMovie();
    HashMap<Integer, Double> sumOfRating = sumOfRating();
    this.topRecommendMovies = new ArrayList<>();

    ArrayList<Integer> top30 = this.sortGetTopN(numberOfMovie, 2*this.limit);
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
  public HashMap<Integer, Integer> numberOfMovie(){
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>();

    for(String userId : this.userList){
      int index = Integer.parseInt(userId) - 1;
      for(String movieIdString : this.userIdIndexList[index].keySet()){
        int weight = this.genreSimilarity(ratingUtils.parseGenre(this.genre) ,this.movieGenreList.get(movieIdString));
        int movieId = Integer.parseInt(movieIdString);
        if(movieId == Integer.parseInt(this.movieId))
          weight = 0;
        if(weight != 0){
          if(this.userIdIndexList[index].get(movieIdString) >= 3){
            if (numberOfMovie.get(movieId) == null) {
              numberOfMovie.put(movieId, weight);
            } else {
              numberOfMovie.put(movieId, numberOfMovie.get(movieId) + weight);
            }
          }
        }
      }
    }
    return numberOfMovie;
  }

  public HashMap<Integer, Double> sumOfRating(){
    HashMap<Integer, Double> sumOfRating = new HashMap<>();
    this.numOfRating = new HashMap<>();
    for(String userId : this.userList){
      int index = Integer.parseInt(userId) - 1;
      for(String movieIdString : this.userIdIndexList[index].keySet()){
        int weight = this.genreSimilarity(ratingUtils.parseGenre(this.genre) ,this.movieGenreList.get(movieIdString));
        int movieId = Integer.parseInt(movieIdString);
        if(movieId == Integer.parseInt(this.movieId))
          weight = 0;
        if(weight != 0){
          if (sumOfRating.get(movieId) == null) {
            sumOfRating.put(movieId, Double.valueOf(this.userIdIndexList[index].get(movieIdString)));
            this.numOfRating.put(movieId, 1);
          } else {
            sumOfRating.put(movieId, sumOfRating.get(movieId) + this.userIdIndexList[index].get(movieIdString));
            this.numOfRating.put(movieId, this.numOfRating.get(movieId)+1);
          }
        }
      }
    }
    return sumOfRating;
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

  public ArrayList<Integer> getTopRecommendMovies(){
    if(this.topRecommendMovies == null)
      recommendMovies();
    return this.topRecommendMovies;
  }

}
