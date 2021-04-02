package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load {

  private String[] categories;
  private String occupation;
  private ArrayList<String> movieList = new ArrayList<String>();
  private ArrayList<String> userList = new ArrayList<String>();

  public Load(String args1, String args2) {
    this.setCategories(args1);
    this.setOccupation(args2);
    this.setMovieList();
    this.setUserList();
  }

  // Return boolean value that compArr has all value of targetArr.
  // If compArr has all values of targetArr return true, or return false.
  public boolean hasContained(String[] truthArr, String[] targetArr) {
    int count = 0;

    for (String targetValue : targetArr) {
      for (String truthValue : truthArr) {
        if (targetValue.toLowerCase().equals(truthValue.toLowerCase())) {
          count++;
        }
      }
    }

    if (targetArr.length != count) {
      return false;
    }

    return true;
  }

  public boolean hasContained(String[] truthArr, String targetArr) {
    for (String truthValue : truthArr) {
      if (truthValue.toLowerCase().equals(targetArr.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  // Because "movies.dat" files has several line which has delimiter "::", we should have separate it properly.
  public String[] parseCategory(String input) {
    return input.split("\\|");
  }

  public int categoriesLength() {
    return this.categories.length;
  }

  public int movieListLength() {
    return this.movieList.size();
  }

  public void setCategories(String categories) {
    this.categories = this.parseCategory(categories);
  }

  public String[] getCategories() {
    return this.categories;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getOccupation() {
    return this.occupation;
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
        String[] fileCategories = this.parseCategory(line.split("::")[2]);

        if (hasContained(fileCategories, this.getCategories())) {
          this.getMovieList().add(line);
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> getUserList() {
    return this.userList;
  }

  public void setUserList() {
    String occupationNumber = "";
    File file = new File("./data/occupation.dat");
    File file2 = new File("./data/users.dat");

    try {
      // In this step, we read "occupation.dat" and transform member variable "occupation" to corresponding String number.
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        String[] fileOccupation = new String[2];

        try {
          String fileOccupation_1 = line.split("::")[1].split("/")[0].replaceAll(" ", "");
          String fileOccupation_2 = line.split("::")[1].split("/")[1].replaceAll(" ", "");
          fileOccupation[0] = fileOccupation_1;
          fileOccupation[1] = fileOccupation_2;
        } catch (Exception e) {
          fileOccupation = line.split("::")[1].split("/");
          fileOccupation[0] = fileOccupation[0].replaceAll(" ", "");
        }

        if (this.hasContained(fileOccupation, this.getOccupation())) {
          occupationNumber = line.split("::")[0];
        }
      }

      // In this step, we read "user.dat" and filter user data using local variable "occupationNumber".
      BufferedReader br2 = new BufferedReader(new FileReader(file2));
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
}