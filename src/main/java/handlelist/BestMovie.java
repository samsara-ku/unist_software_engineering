package handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class BestMovie {

  private final ArrayList<Integer> top10 = new ArrayList<Integer>();
  private final ArrayList<Integer> top10_num = new ArrayList<Integer>();
  private final ArrayList<Double> top10_rat = new ArrayList<Double>();
  private HashMap<String, Integer>[] userIdIndexList;
  private HashMap<String, String[]> movieGenreList;

  public BestMovie(HashMap<String, Double> userList) {
    this.setUserIdIndexList();
    this.setTop10(userList);
  }

  public BestMovie(HashMap<String, Double> userList, String genre) {
    this.setMovieGenreList();
    this.setUserIdIndexList(parseGenre(genre));
    this.setTop10(userList);
  }

  public String[] parseGenre(String genre) {
    return genre.contains("|") ? genre.split("\\|") : new String[]{genre};
  }

  public void setMovieGenreList() {
    File file = new File("./data/movies.dat");
    movieGenreList = new HashMap<String, String[]>();

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

  public HashMap<String, String[]> getMovieGenreList() { return this.movieGenreList; }

  public void setUserIdIndexList() {
    int intUserIdIndex;
    int userSize = getFileListSize("./data/users.dat");

    this.userIdIndexList = new HashMap[userSize];

    for (int i = 0; i < userSize; i++) {
      this.userIdIndexList[i] = new HashMap<String, Integer>();
    }

    File file = new File("./data/ratings.dat");

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        String[] parseLine = line.split("::");
        intUserIdIndex = Integer.parseInt(parseLine[0]) - 1;
        this.userIdIndexList[intUserIdIndex].put(parseLine[1], Integer.parseInt(parseLine[2]));
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setUserIdIndexList(String[] genreList) {
    int userSize = getFileListSize("./data/users.dat");
    int intUserIdIndex;

    this.userIdIndexList = new HashMap[userSize];

    for (int i = 0; i < userSize; i++) {
      this.userIdIndexList[i] = new HashMap<String, Integer>();
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
              this.userIdIndexList[intUserIdIndex]
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
  }


  /* get original movie list size from file */
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

  public ArrayList<Integer> getTop10() {
    return this.top10;
  }

  // Find the best 10 movies to recommend
  public void setTop10(HashMap<String, Double> userList) {
    double sumOfWeight = 0;
    int numOfWeight = 0;
    int movieIndexSize = getFileListSize("./data/movies.dat");
    HashMap<Integer, Integer> numberOfMovie = new HashMap<>(movieIndexSize);
    HashMap<Integer, Double> sumOfRating = new HashMap<>(movieIndexSize);

    // Count the number of movie watched by users, and calculate rating
    for (HashMap.Entry<String, Double> user : userList.entrySet()) {
      int index = Integer.parseInt(user.getKey()) - 1;

      for (HashMap.Entry<String, Integer> entry : this.userIdIndexList[index].entrySet()) {
        int movieID = Integer.parseInt(entry.getKey());
        int rating = entry.getValue();
        if (numberOfMovie.get(movieID) == null) {
          numberOfMovie.put(movieID, 1);
        } else {
          numberOfMovie.put(movieID, numberOfMovie.get(movieID) + 1);
        }

        if (sumOfRating.get(movieID) == null) {
          double sum = rating * user.getValue();
          sumOfWeight += user.getValue();
          numOfWeight++;
          sumOfRating.put(movieID, sum);
        } else {
          double sum = sumOfRating.get(movieID) + rating * user.getValue();
          sumOfWeight += user.getValue();
          numOfWeight++;
          sumOfRating.put(movieID, sum);
        }
      }
    }

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

    HashMap<Integer, Double> avgRating = new HashMap<>(30);

    // Calculate average rating
    for (int movieID : top30) {
      double sum = sumOfRating.get(movieID);
      int num = numberOfMovie.get(movieID);
      double avg_rating = sum / (double) num;
      double avg_weight = sumOfWeight / (double) numOfWeight;

      avgRating.put(movieID, avg_rating / avg_weight);
    }

    // Sorting avgRating (descending order)
    List<Entry<Integer, Double>> sortedRating = new ArrayList<Entry<Integer, Double>>(
        avgRating.entrySet());

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