package state.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadMoreThanTwoArgs extends Load implements LoadAction {

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
    String occupationNumber = "";

    if (!this.occupation.isBlank()) {
      File file_o = new File("./data/occupation.dat");

      try {
        // In this step, we read "occupation.dat" and transform member variable "occupation" to corresponding String number.
        BufferedReader br = new BufferedReader(new FileReader(file_o));
        String line;

        while ((line = br.readLine()) != null) {
          ArrayList<String> fileOccupation = new ArrayList<>();

          try {
            String fileOccupation_1 = line.split("::")[1].split("/")[0].replaceAll(" ", "");
            String fileOccupation_2 = line.split("::")[1].split("/")[1].replaceAll(" ", "");
            fileOccupation.add(fileOccupation_1);
            fileOccupation.add(fileOccupation_2);
          } catch (Exception e) {
            fileOccupation.add(line.split("::")[1].split("/")[0].replaceAll(" ", ""));
          }

          if (this.hasContained(fileOccupation, this.getOccupation())) {
            occupationNumber = line.split("::")[0];
          }
        }

        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // user detection
    File file = new File("./data/users.dat");

    try {
      // In this step, we read "user.dat" and filter user data using local variable "occupationNumber".
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        double weight = 1;
        boolean isSameGender = line.split("::")[1].equals(gender);
        boolean isSameOccupation = line.split("::")[3].equals(occupationNumber);

        if (!((isSameGender || this.gender.isBlank()) && (isSameOccupation || this.occupation.isBlank()))) {
          if (Math.random() >= 0.25) {
            continue;
          }

          if (!isSameOccupation && !this.occupation.isBlank()) {
            weight *= 0.75;
          }
          if (!isSameGender && !this.gender.isBlank()) {
            weight *= 0.5;
          }
        }

        String userId = line.split("::")[0];
        String num = line.split("::")[2];
        int Age = (this.age.isBlank()) ? 0 : Integer.parseInt(this.age);

        if (this.age.isBlank()) {
          this.getUserList().put(userId, weight);
        } else if (Age < 18) {
          if (num.equals("1")) {
            this.getUserList().put(userId, weight);
          }
        } else if (Age < 25) {
          if (num.equals("18")) {
            this.getUserList().put(userId, weight);
          }
        } else if (Age < 35) {
          if (num.equals("25")) {
            this.getUserList().put(userId, weight);
          }
        } else if (Age < 45) {
          if (num.equals("35")) {
            this.getUserList().put(userId, weight);
          }
        } else if (Age < 50) {
          if (num.equals("45")) {
            this.getUserList().put(userId, weight);
          }
        } else if (Age < 56) {
          if (num.equals("50")) {
            this.getUserList().put(userId, weight);
          }
        } else {
          if (num.equals("56")) {
            this.getUserList().put(userId, weight);
          }
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
