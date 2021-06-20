package factory.link;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LinkUserAndRating {

  private final ArrayList<String> LinkList = new ArrayList<>();

  public LinkUserAndRating(ArrayList<Integer> top10, int limit) {
    this.setLinkList(top10, limit);
  }

  public ArrayList<String> getLinkList() {
    return this.LinkList;
  }

  public void setLinkList(ArrayList<Integer> topMovies, int limit) {
    int idx = 0;

    // Find movie name and link from .dat file with movieID
    for (Integer movieID : topMovies) {
      Resource targetFileResource = new ClassPathResource("data/links.dat");
      Resource targetFileResource2 = new ClassPathResource("data/movies.dat");
      File file = null;
      File file2 = null;
      try {
        file = targetFileResource.getFile();
        file2 = targetFileResource2.getFile();
      } catch (IOException e) {
        e.printStackTrace();
      }

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
            if (idx == limit - 1) {
              break;
            }
          }
        }

        while ((line2 = br2.readLine()) != null) {
          int ID = Integer.parseInt(line2.split("::")[0]);
          String name = line2.split("::")[1];
          String genre = line2.split("::")[2];

          if (movieID == ID) {
            String link = this.getLinkList().get(idx);
            String add = name + "/" + link + "/" + genre;
            this.getLinkList().set(idx, add);

            if (idx == limit - 1) {
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