package factory.rating;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In;

public class RatingUtils {

  /* Set ArrayList that only has Id */
  public ArrayList<String> getIdList(ArrayList<String> list) {
    return new ArrayList<String>(list.stream().map(e -> e.split("::")[0])
        .collect(Collectors.toList()));
  }

  /* Set RatingList from file */
  public ArrayList<String> getRatingList() {
    ArrayList<String> ratingList = new ArrayList<>();
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
    return ratingList;
  }


  /* get original list size from file */
  public int getFileListSize(String path) {
    File file = new File(path);
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

  public HashMap<String, String[]> getMovieGenreList() {
    File file = new File("./data/movies.dat");
    HashMap<String, String[]> movieGenreList = new HashMap<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        String[] parseLine = line.split("::");
        movieGenreList.put(parseLine[0], parseGenre(parseLine[2]));
      }
      br.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return movieGenreList;
  }

  public String[] parseGenre(String genre) {
    return genre.split("\\|");
  }

  public HashMap<String, String>[] getMovieIdIndexList() {
    int movieIndexSize = getFileListSize("./data/movies.dat");
    int intMovieIdIndex;
    /* Generate map array which can include every movieId */
    HashMap<String, String>[] movieIdIndexList = new HashMap[movieIndexSize];
    for (int i = 0; i < movieIndexSize; i++) {
      movieIdIndexList[i] = new HashMap<>();
    }

    /* Store userId and rating corresponding to movieId of movieIdIndexList */
    for (String rateLine : getRatingList()) {
      String[] parseLine = rateLine.split("::");
      intMovieIdIndex = Integer.parseInt(parseLine[1]) - 1;
      movieIdIndexList[intMovieIdIndex].put(parseLine[0], parseLine[2]);
    }
    return movieIdIndexList;
  }

  public HashMap<String, Integer>[] setUserIdIndexList() {
    int intUserIdIndex;
    int userSize = getFileListSize("./data/users.dat");

    HashMap<String, Integer>[] userIdIndexList = new HashMap[userSize];

    for (int i = 0; i < userSize; i++) {
      userIdIndexList[i] = new HashMap<>();
    }

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
    return userIdIndexList;
  }

  public HashMap<String, Integer>[] setUserIdIndexList(String[] genreList) {
    int userSize = getFileListSize("./data/users.dat");
    int intUserIdIndex;
    HashMap<String, String[]> movieGenreList = getMovieGenreList();

    HashMap<String, Integer>[] userIdIndexList = new HashMap[userSize];
    for (int i = 0; i < userSize; i++) {
      userIdIndexList[i] = new HashMap<>();
    }
    File file = new File("./data/ratings.dat");

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      while ((line = br.readLine()) != null) {
        String[] parseLine = line.split("::");
        intUserIdIndex = Integer.parseInt(parseLine[0]) - 1;

        for (String genreListValue : genreList) {
          boolean isPut = false;
          for (String genreValue : movieGenreList.get(parseLine[1])) {
            if (genreListValue.equalsIgnoreCase(genreValue)) {
              userIdIndexList[intUserIdIndex]
                  .put(parseLine[1], Integer.parseInt(parseLine[2]));
              isPut = true;
              break;
            }
          }
          if (isPut) {
            break;
          }
        }
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return userIdIndexList;
  }

  public List<Entry<Integer, Double>> getMovieRankingList(int rank) {
    int movieIndexSize = getFileListSize("./data/movies.dat");
    int intMovieIdIndex;
    HashMap<Integer, Double> movieRankingList = new HashMap();
    HashMap<Integer, Double> numOfRating = new HashMap();

    for (String rateLine : getRatingList()) {
      String[] parseLine = rateLine.split("::");
      Integer movieId = Integer.parseInt(parseLine[1]);
      if (movieRankingList.get(movieId) == null) {
        movieRankingList.put(movieId, Double.valueOf(parseLine[2]));
        numOfRating.put(movieId, 1.0);
      } else {
        movieRankingList.put(movieId,
            movieRankingList.get(movieId) + Double.valueOf(parseLine[2]));
        numOfRating.put(movieId, numOfRating.get(movieId) + 1);
      }
    }

    List<Entry<Integer, Double>> sortedViewers = new ArrayList<>(
        numOfRating.entrySet());
    sortedViewers.sort((obj1, obj2) -> obj2.getValue().compareTo(obj1.getValue()));

    //Sort list with number of viewers
    List<Entry<Integer, Double>> sortedViewersTop = new ArrayList<>(sortedViewers.subList(0, rank*3));

    HashMap<Integer, Double> avgRating = new HashMap<>();

    //Get average rating
    for (Entry<Integer, Double> movieId : sortedViewersTop) {
      avgRating
          .put(movieId.getKey(), movieRankingList.get(movieId.getKey()) / numOfRating.get(movieId.getKey()));
    }

    //Sort list with rating
    List<Entry<Integer, Double>> sortedRating = new ArrayList<>(
        avgRating.entrySet());
    sortedRating.sort((obj1, obj2) -> obj2.getValue().compareTo(obj1.getValue()));

    List<Entry<Integer, Double>> rankingList = new ArrayList<>(sortedRating.subList(0, rank));

    return rankingList;
  }
}
