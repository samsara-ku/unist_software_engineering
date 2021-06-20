package factory.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LoadMovieTitle {

  private final ArrayList<String> userList;
  private final String movieTitle;
  private String movieId = "";
  private String movieGenre = "";

  public LoadMovieTitle(String movieTitle) {
    this.userList = new ArrayList<>();
    this.movieTitle = movieTitle;
  }

  public void setUserWatchedMovie() {
    Resource targetFileResource = new ClassPathResource("data/ratings.dat");
    File file = null;

    try {
      file = targetFileResource.getFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.setMovieIdAndGenre();
    if (this.movieId != "0") {
      try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
          String[] parsedLine = line.split("::");
          String movieIdData = parsedLine[0];
          String userId = parsedLine[1];
          int rating = Integer.parseInt(parsedLine[2]);

          //movie에 3점 이상 rating을 준 user들만 추출
          if (this.movieId.equals(movieIdData) && rating >= 3) {
            this.userList.add(userId);
          }
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setMovieIdAndGenre() {
    Resource targetFileResource = new ClassPathResource("data/movies.dat");
    File file = null;

    try {
      file = targetFileResource.getFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.movieId = "0";

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        String movieTitleData = this.parseMovieTitle(line.split("::")[1]);
        String movieTitleInput = this.parseMovieTitle(this.movieTitle);

        if (movieTitleInput.equals(movieTitleData)) {
          this.movieId = line.split("::")[0];
          this.movieGenre = line.split("::")[2];
        }
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> getUserList() {
    setUserWatchedMovie();
    return this.userList;
  }

  public String getMovieId() {
    if (this.movieId.isEmpty()) {
      setMovieIdAndGenre();
    }
    return this.movieId;
  }

  public String getMovieGenre() {
    if (this.movieGenre.isEmpty()) {
      setMovieIdAndGenre();
    }
    return this.movieGenre;
  }

  public String parseMovieTitle(String title) {
    return title.toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
  }

}
