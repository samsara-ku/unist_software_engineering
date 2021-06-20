package factory.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

abstract class Load {

  public boolean hasContained(ArrayList<String> truthArr, ArrayList<String> target) {
    return truthArr.stream().filter(i -> target.stream().anyMatch(e -> e.equalsIgnoreCase(i)))
        .count() == target.size();
  }

  public boolean hasContained(ArrayList<String> truthArr, String target) {
    return truthArr.stream().anyMatch(i -> i.equalsIgnoreCase(target));
  }

  public String findOccupationNumber(String occupation) {
    Resource targetFileResource = new ClassPathResource("data/occupation.dat");
    File occu_file = null;

    try {
      occu_file = targetFileResource.getFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String occupationNumber = null;

    try {
      // In this step, we read "occupation.dat" and transform member variable "occupation" to corresponding String number.
      BufferedReader br = new BufferedReader(new FileReader(occu_file));
      String line;

      while ((line = br.readLine()) != null) {
        ArrayList<String> fileOccupation = new ArrayList<>();

        try {
          Arrays.stream(line.split("::")[1].split("/")).forEach(fileOccupation::add);
        } catch (Exception e) {
          fileOccupation.add(line.split("::")[1].split("/")[0].replaceAll(" ", ""));
        }

        if (this.hasContained(fileOccupation, occupation)) {
          occupationNumber = line.split("::")[0];
        }
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return occupationNumber;
  }

  public abstract void setUserList();
}
