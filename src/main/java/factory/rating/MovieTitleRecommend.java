package factory.rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MovieTitleRecommend extends RecommendMovieAbstract {

  private final RatingUtils ratingUtils;
  private final ArrayList<String> userList;
  private final int limit;
  private final String genre;
  private final String movieId;
  private ArrayList<Integer> topRecommendMovies;
  private HashMap<String, Integer>[] userIdIndexList;
  private HashMap<String, String[]> movieGenreList;
  private HashMap<Integer, Integer> numOfRating;

  public MovieTitleRecommend(ArrayList<String> userList, int limit, String movieId, String genre) {
    this.ratingUtils = new RatingUtils();
    this.userList = userList;
    this.limit = limit;
    this.genre = genre;
    this.movieId = movieId;
  }

  public void recommendMovies() {
    this.userIdIndexList = ratingUtils.setUserIdIndexList();
    this.movieGenreList = ratingUtils.getMovieGenreList();

    HashMap<Integer, Integer> numberOfMovie = numberOfMovie();
    HashMap<Integer, Double> sumOfRating = sumOfRating();
    this.topRecommendMovies = new ArrayList<>();

    //user들이 남긴 movie rating의 개수로 정렬 후 limit의 두배 크기의 리스트 생성
    ArrayList<Integer> topDoubleLimit = this.sortGetTopN(numberOfMovie, 2 * this.limit);

    //topDoubleLimit list 영화들의 평균 rating을 계산
    HashMap<Integer, Double> ratingList = new HashMap<>();
    for (Integer top30MovieId : topDoubleLimit) {
      ratingList
          .put(top30MovieId, sumOfRating.get(top30MovieId) / this.numOfRating.get(top30MovieId));
    }

    //평균 rating으로 최종 정렬 후 리스트 생성
    List<Entry<Integer, Double>> sortedRating = sortWithRating(ratingList);

    int idx = 0;

    for (Entry<Integer, Double> entry : sortedRating) {
      if (idx < this.limit) {
        this.topRecommendMovies.add(entry.getKey());
        idx++;
      }
    }
    if (this.topRecommendMovies.size() < this.limit) {

      for (Entry<String, Double> movieId : ratingUtils.getMovieRankingList(1000)) {
        if (numberOfMovie.get(Integer.parseInt(movieId.getKey())) == null) {
          this.topRecommendMovies.add(Integer.parseInt(movieId.getKey()));
          if (this.topRecommendMovies.size() >= this.limit) {
            break;
          }
        }
      }
    }
  }


  public HashMap<Integer, Integer> numberOfMovie() {
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>();

    for (String userId : this.userList) {
      int index = Integer.parseInt(userId) - 1;
      for (String movieIdString : this.userIdIndexList[index].keySet()) {

        //장르 유사도가 높을수록 가중치를 부여함
        int weight = this.genreSimilarity(ratingUtils.parseGenre(this.genre),
            this.movieGenreList.get(movieIdString));
        int movieId = Integer.parseInt(movieIdString);

        //동일 영화 제외
        if (movieId == Integer.parseInt(this.movieId)) {
          weight = 0;
        }
        if (weight != 0) {

          //3점 이상의 rating만 인정하여 영화 시청수에 추가
          if (this.userIdIndexList[index].get(movieIdString) >= 3) {
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

  public HashMap<Integer, Double> sumOfRating() {
    HashMap<Integer, Double> sumOfRating = new HashMap<>();
    this.numOfRating = new HashMap<>();

    for (String userId : this.userList) {
      int index = Integer.parseInt(userId) - 1;

      for (String movieIdString : this.userIdIndexList[index].keySet()) {
        int weight = this.genreSimilarity(ratingUtils.parseGenre(this.genre),
            this.movieGenreList.get(movieIdString));
        int movieId = Integer.parseInt(movieIdString);

        if (movieId == Integer.parseInt(this.movieId)) {
          weight = 0;
        }
        if (weight != 0) {
          if (sumOfRating.get(movieId) == null) {
            sumOfRating
                .put(movieId, Double.valueOf(this.userIdIndexList[index].get(movieIdString)));
            this.numOfRating.put(movieId, 1);
          } else {
            sumOfRating.put(movieId,
                sumOfRating.get(movieId) + this.userIdIndexList[index].get(movieIdString));
            this.numOfRating.put(movieId, this.numOfRating.get(movieId) + 1);
          }
        }
      }
    }
    return sumOfRating;
  }

  public int genreSimilarity(String[] inputGenreList, String[] targetGenreList) {
    int similarity = 0;

    for (String inputGenre : inputGenreList) {
      for (String targetGenre : targetGenreList) {

        //장르가 많이 겹칠수록 유사도 증가
        if (inputGenre.equals(targetGenre)) {
          if (similarity == 0) {
            similarity = 1;
          } else {
            similarity *= 3;
          }
        }
      }
    }
    return similarity;
  }

  public ArrayList<Integer> getTopRecommendMovies() {
    if (this.topRecommendMovies == null) {
      recommendMovies();
    }
    return this.topRecommendMovies;
  }

}
