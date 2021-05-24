package factory.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoadMoreThanTwoArgs extends Load {

  private final HashMap<String, Double> userList = new HashMap<>();
  private String gender;
  private String age;
  private String occupation;


  public LoadMoreThanTwoArgs(String gender, String age, String occupation) {
    this.setGender(gender);
    this.setAge(age);
    this.setOccupation(occupation);
    this.setUserList();
  }

  public String getAge() {
    return this.age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getOccupation() {
    return this.occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public HashMap<String, Double> getUserList() {
    return this.userList;
  }

  public void setUserList() {
    String occupationNumber =
        !this.occupation.isBlank() ? this.findOccupationNumber(this.getOccupation()) : "";
    File user_file = new File("./data/users.dat");

    try {
      // In this step, we read "user.dat" and filter user data using local variable "occupationNumber".
      BufferedReader br = new BufferedReader(new FileReader(user_file));
      String line;

      while ((line = br.readLine()) != null) {
        double weight = 1;
        boolean isProperGender = line.split("::")[1].equalsIgnoreCase(this.gender) || this.gender
            .isBlank(); // check whether gender is equal with gender of data or it is blank.
        boolean isProperOccupation =
            line.split("::")[3].equals(occupationNumber) || this.occupation
                .isBlank(); // check whether occupation is equal with occupation of data or it is blank.

        if (!(isProperGender && isProperOccupation)) {
          // For randomly select user
          if (Math.random() >= 0.25) {
            continue;
          }

          weight = !isProperOccupation ? weight * 0.75 : weight;
          weight = !isProperGender ? weight * 0.5 : weight;
        }
        findUserWithProperAge(line, weight);
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void findUserWithProperAge(String line, double weight) {
    String userId_data = line.split("::")[0];
    String age_data = line.split("::")[2];
    int age_input = (this.age.isBlank()) ? 0 : Integer.parseInt(this.age);

    if (age_input == 0) {
      this.getUserList().put(userId_data, weight);
    } else if (age_input < 18) {
      if (age_data.equals("1")) {
        this.getUserList().put(userId_data, weight);
      }
    } else if (age_input < 25) {
      if (age_data.equals("18")) {
        this.getUserList().put(userId_data, weight);
      }
    } else if (age_input < 35) {
      if (age_data.equals("25")) {
        this.getUserList().put(userId_data, weight);
      }
    } else if (age_input < 45) {
      if (age_data.equals("35")) {
        this.getUserList().put(userId_data, weight);
      }
    } else if (age_input < 50) {
      if (age_data.equals("45")) {
        this.getUserList().put(userId_data, weight);
      }
    } else if (age_input < 56) {
      if (age_data.equals("50")) {
        this.getUserList().put(userId_data, weight);
      }
    } else {
      if (age_data.equals("56")) {
        this.getUserList().put(userId_data, weight);
      }
    }
  }
}
