package state.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoadTwoArgs extends Load implements LoadAction {

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
    String occupationNumber = "";
    File occu_file = new File("./data/occupation.dat");
    File user_file = new File("./data/users.dat");

    try {
      // In this step, we read "occupation.dat" and transform member variable "occupation" to corresponding String number.
      BufferedReader br = new BufferedReader(new FileReader(occu_file));
      String line;

      while ((line = br.readLine()) != null) {
        ArrayList<String> fileOccupation = new ArrayList<>();

        try {
          Arrays.stream(line.split("::")[1].split("/")).forEach(i -> fileOccupation.add(i));
        } catch (Exception e) {
          fileOccupation.add(line.split("::")[1].split("/")[0].replaceAll(" ", ""));
        }

        if (this.hasContained(fileOccupation, this.getOccupation())) {
          occupationNumber = line.split("::")[0];
        }
      }

      // In this step, we read "user.dat" and filter user data using local variable "occupationNumber".
      BufferedReader br2 = new BufferedReader(new FileReader(user_file));
      String line2;

      while ((line2 = br2.readLine()) != null) {
        if (line2.split("::")[3].equals(occupationNumber)) {
          this.getUserList().add(line2);
        }
      }

      br.close();
      br2.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> getMovieList() {
    return this.movieList;
  }

  public void setMovieList() {
    File file = new File("./data/movies.dat");

    try {
      // In this step, we read "movies.dat" and filter movie data properly using member variable "categories".
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        ArrayList<String> fileCategories = this.parseCategory(line.split("::")[2]);

        if (hasContained(fileCategories, this.getCategories())) {
          this.getMovieList().add(line);
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
