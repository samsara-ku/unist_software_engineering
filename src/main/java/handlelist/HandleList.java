package handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HandleList {

  /* Set ArrayList that only has movieId */
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

  public HashMap<String, String>[] getMoiveIdIndexList(){
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
}
