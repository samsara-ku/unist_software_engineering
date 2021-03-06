package factory.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LoadTwoArgs extends Load {

  private final ArrayList<String> userList = new ArrayList<>();
  private final ArrayList<String> movieList = new ArrayList<>();
  private ArrayList<String> categories;
  private String occupation;

  public LoadTwoArgs(String categories, String occupation) {
    this.setCategories(categories);
    this.setOccupation(occupation);
    this.setUserList();
    this.setMovieList();
  }

  public ArrayList<String> parseCategory(String input) {
    return new ArrayList<>(Arrays.asList(input.split("\\|")));
  }

  public ArrayList<String> getCategories() {
    return this.categories;
  }

  public void setCategories(String categories) {
    this.categories = this.parseCategory(categories);
  }

  public String getOccupation() {
    return this.occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }


  public ArrayList<String> getUserList() {
    return this.userList;
  }

  public void setUserList() {
    String occupationNumber = this.findOccupationNumber(this.getOccupation());

    Resource targetFileResource = new ClassPathResource("data/users.dat");
    File user_file = null;

    try {
      user_file = targetFileResource.getFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      // In this step, we read "user.dat" and filter user data using local variable "occupationNumber".
      BufferedReader br = new BufferedReader(new FileReader(user_file));
      String line;

      while ((line = br.readLine()) != null) {
        if (line.split("::")[3].equals(occupationNumber) || occupationNumber == null) {
          this.getUserList().add(line);
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> getMovieList() {
    return this.movieList;
  }

  public void setMovieList() {
    Resource targetFileResource = new ClassPathResource("data/movies.dat");
    File file = null;

    try {
      file = targetFileResource.getFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      // In this step, we read "movies.dat" and filter movie data properly using member variable "categories".
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        ArrayList<String> fileCategories = this.parseCategory(line.split("::")[2]);

        if (hasContained(fileCategories, this.getCategories()) || this.getCategories().size() == 0) {
          this.getMovieList().add(line);
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
