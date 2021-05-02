package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadLink {

  private ArrayList<String> LinkList = new ArrayList<String>();

  public LoadLink(ArrayList<Integer> top10) {

    this.setLinkList(top10);
  }

  public ArrayList<String> getLinkList() {
    return this.LinkList;
  }

  public void setLinkList(ArrayList<Integer> top10) {

    int idx = 0;

    // Find movie name and link from .dat file with movieID
    for (Integer movieID : top10) {
      File file = new File("./data/links.dat");
      File file2 = new File("./data/movies.dat");

      try {
        // In this step, we read "movies.dat" and filter movie data properly using member variable "categories".
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        String line;
        String line2;

        while ((line = br.readLine()) != null) {
          int ID = Integer.parseInt(line.split("::")[0]);
          String link = line.split("::")[1];

          if (movieID == ID) {
            this.getLinkList().add(link);
            if (idx == 9) {
              break;
            }
          }
        }

        while ((line2 = br2.readLine()) != null) {
          int ID = Integer.parseInt(line2.split("::")[0]);
          String name = line2.split("::")[1];

          if (movieID == ID) {
            String link = this.getLinkList().get(idx);
            String add = name + "/" + link;
            this.getLinkList().set(idx, add);

            if (idx == 9) {
              break;
            }

            idx = idx + 1;
          }
        }
        br.close();
        br2.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}