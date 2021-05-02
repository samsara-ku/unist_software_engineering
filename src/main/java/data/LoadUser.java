package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadUser {

  private final ArrayList<String> userList = new ArrayList<>();

  public LoadUser(String gender, String age, String occupation) {
    this.setUserList(gender, age, occupation);
  }

  public boolean hasContained(String[] truthArr, String targetArr) {
    for (String truthValue : truthArr) {
      if (truthValue.equalsIgnoreCase(targetArr)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<String> getUserList() {
    return this.userList;
  }

  // find user with input conditions
  public void setUserList(String gender, String age, String occupation) {
    String occupationNumber = "";

    if (!occupation.isBlank()) {
      File file_o = new File("./data/occupation.dat");

      try {
        // In this step, we read "occupation.dat" and transform member variable "occupation" to corresponding String number.
        BufferedReader br = new BufferedReader(new FileReader(file_o));
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

          if (this.hasContained(fileOccupation, occupation)) {
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
        // If gender, occupation input don't exist, pass for all cases.
        if (((line.split("::")[1].equals(gender)) || gender.isBlank()) && ((
            line.split("::")[3].equals(occupationNumber) || occupation.isBlank()))) {

          String num = line.split("::")[2];
          int Age = (age.isBlank()) ? 0 : Integer.parseInt(age);

          if (age.isBlank()) {
            this.getUserList().add(line.split("::")[0]);
          } else if (Age < 18) {
            if (num.equals("1")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (Age < 25) {
            if (num.equals("18")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (Age < 35) {
            if (num.equals("25")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (Age < 45) {
            if (num.equals("35")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (Age < 50) {
            if (num.equals("45")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (Age < 56) {
            if (num.equals("50")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else {
            if (num.equals("56")) {
              this.getUserList().add(line.split("::")[0]);
            }
          }
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}