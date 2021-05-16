package handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HandleList {

  /* Set ArrayList that only has Id */
  public ArrayList<String> getIdList(ArrayList<String> list) {
    ArrayList<String> newIdList = new ArrayList<String>();
    for (String listLine : list) {
      String temp = listLine.split("::")[0];
      newIdList.add(temp);
    }
    return newIdList;
  }

  /* Set RatingList from file */
  public ArrayList<String> getRatingList() {
    ArrayList<String> ratingList = new ArrayList<String>();
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
    HashMap<String, String[]> movieGenreList = new HashMap<String, String[]>();

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
    return genre.contains("|") ? genre.split("\\|") : new String[]{genre};
  }

  public HashMap<String, String>[] getMovieIdIndexList() {
    int movieIndexSize = getFileListSize("./data/movies.dat");
    int intMovieIdIndex;
    /* 모든 movieId를 포함할 수 있는 크기의 map Array 생성 */
    HashMap<String, String>[] movieIdIndexList = new HashMap[movieIndexSize];
    for (int i = 0; i < movieIndexSize; i++) {
      movieIdIndexList[i] = new HashMap<String, String>();
    }

    /* movieIdIndexList의 movieId에 해당하는 곳에 userid와 rating을 저장 */
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
      userIdIndexList[i] = new HashMap<String, Integer>();
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
      userIdIndexList[i] = new HashMap<String, Integer>();
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
}
