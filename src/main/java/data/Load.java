package data;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
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

    public String neutralizeString(String input) {
        return StringUtils.capitalize(input.toLowerCase());
    }

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

        for (int i=0; i<this.categoriesLength(); i++) {
            this.categories[i] = this.neutralizeString(this.categories[i]);
        }
    }

    public String[] getCategories() {
        return this.categories;
    }

    public void setOccupation(String occupation) {
        this.occupation = this.neutralizeString(occupation);
    }

    public String getOccupation() {
        return this.occupation;
    }

    public ArrayList<String> getMovieList() {
        return this.movieList;
    }

    public void setMovieList () {
        File file = new File("./data/movies.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                // For checking entire user input. e.g) Adventure|Comedy ==> Data in movieList should be count == 2 case.
                int count = 0;

                for (int i=0; i<this.categoriesLength(); i++) {
                    if (line.contains(this.categories[i])) {
                        count++;
                    }
                }

                if (count == this.categoriesLength()) {
                    System.out.println(line);
                    this.movieList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getUserList() {
        return this.userList;
    }

    public void setUserList() {
        String occupationNumber = new String();
        File file = new File("./data/occupation.dat");
        File file2 = new File("./data/users.dat");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(this.getOccupation().toLowerCase())) {
                    occupationNumber = line.split("::")[0];
                }
            }

            if (occupationNumber == null) {
                occupationNumber = "0";
            }

            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            String line2;

            while ((line2 = br2.readLine()) != null) {
                if (line2.split("::")[3].equals(occupationNumber)) {
                    userList.add(line2);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}