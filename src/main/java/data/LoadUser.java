package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadUser {

  private String gender;
  private String age;
  private String occupation;
  private ArrayList<String> userList = new ArrayList<String>();

  public LoadUser(String args1, String args2, String args3) {
    this.setGender(args1);
    this.setAge(args2);
    this.setOccupation(args3);
    this.setUserList(args1, args2, args3);
  }

  public boolean hasContained(String[] truthArr, String targetArr) {
    for (String truthValue : truthArr) {
      if (truthValue.toLowerCase().equals(targetArr.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getGender() {
    return this.gender;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAge() {
    return this.age;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getOccupation() {
    return this.occupation;
  }

  public ArrayList<String> getUserList() {
    return this.userList;
  }

  // find user with input conditions
  public void setUserList(String gender, String age, String occupation) {

    // Check whether each input exist or not
    boolean genderPass = (gender.equals("")) ? true : false;
    boolean agePass = (age.equals("")) ? true : false;
    boolean occupationPass = (occupation.equals("")) ? true : false;

    String occupationNumber = "";

    // If occupation input exists
    if (!occupationPass) {

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

          if (this.hasContained(fileOccupation, this.getOccupation().toLowerCase())) {
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
        if (((line.split("::")[1].equals(gender)) || genderPass) && ((
            line.split("::")[3].equals(occupationNumber) || occupationPass))) {

          String num = line.split("::")[2];
          Integer Age = (agePass) ? 0 : Integer.parseInt(age);

          if (agePass) {
            this.getUserList().add(line.split("::")[0]);
          } else if (Age < 18) {
            if (num.equals("1")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (18 <= Age && Age < 25) {
            if (num.equals("18")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (25 <= Age && Age < 35) {
            if (num.equals("25")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (35 <= Age && Age < 45) {
            if (num.equals("35")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (45 <= Age && Age < 50) {
            if (num.equals("45")) {
              this.getUserList().add(line.split("::")[0]);
            }
          } else if (50 <= Age && Age < 56) {
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