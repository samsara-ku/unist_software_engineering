package data;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Load {
    private String[] categories;
    private String occupation;
    public ArrayList<String> movieList;

    public Load(String args1, String args2) {
        this.setCategories(args1);
        this.setOccupation(this.neutralizeString(args2));
        this.movieList = new ArrayList<String> ();
    }

    public void printMovieList () {
        File file = new File("./data/movies.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                int count = 0;

                for (int i=0; i<this.categoriesLength(); i++) {
                    if (line.contains(this.categories[i])) {
                        count++;
                    }
                }

                if (count == this.categoriesLength()) {
                    this.movieList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTotalScore () {
        File file = new File("./data/ratings.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int sum = 0;
            int count = 0;

            while ((line = br.readLine()) != null) {
                String[] parseLine = line.split("::");

                for (int i=0; i<this.getMovieLength(); i++) {
                    String[] temp = this.movieList.get(i).split("::");

                    if (parseLine[1] == temp[0]) {
                        System.out.println(line);
                        sum += Integer.parseInt(parseLine[2]);
                        count++;
                    }
                }
            }
            System.out.println("Sum: " + sum);
            System.out.println("Count: " + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMovieLength () {
        return this.movieList.size();
    }

    public String neutralizeString (String input) {
        return StringUtils.capitalize(input.toLowerCase());
    }

    public String[] parseCategory(String input) {
        return input.split("\\|");
    }

    public int categoriesLength() {
        return this.categories.length;
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
        this.occupation = occupation;
    }

    public String getOccupation() {
        return this.occupation;
    }
}