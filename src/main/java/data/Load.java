package data;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    public boolean hasContained (String[] compArr, String[] targetArr) {
        for(String value: targetArr) {
            if (!Arrays.asList(compArr).contains(value)) {
                return false;
            }
        }
        return true;
    }

    // Due to various input, we have to make our member variables to consistently.
    // This function capitalizes only first letter, the others are small letters.
    public String neutralizeString(String input) {
        return StringUtils.capitalize(input.toLowerCase());
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
            // In this step, we read "movies.dat" and filter movie data properly using member variable "categories".
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] fileCategories = this.parseCategory(line.split("::")[2]);

                if (hasContained(fileCategories, this.getCategories())) {
                    this.getMovieList().add(line);
                }
            }
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
                String[] fileOccupation = line.split("::")[1].split("/");

                if (this.hasContained(fileOccupation, new String[] {this.getOccupation().toLowerCase()})) {
                    occupationNumber = line.split("::")[0];
                }
            }

            // If occupation search is failed, default value should be 0
            if (StringUtils.isEmpty(occupationNumber)) {
                occupationNumber = "0";
            }

            // In this step, we read "user.dat" and filter user data using local variable "occupationNumber".
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            String line2;

            while ((line2 = br2.readLine()) != null) {
                if (line2.split("::")[3].equals(occupationNumber)) {
                    this.getUserList().add(line2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}