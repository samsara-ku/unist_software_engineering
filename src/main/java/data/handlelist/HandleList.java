package data.handlelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HandleList {
    private final ArrayList<String> movieList;
    private final ArrayList<String> userList;

    /* We use movie id and user id to filtering ratingList*/
    private ArrayList<String> movieIdList;
    private ArrayList<String> userIdList;
    private ArrayList<String> ratingList;

    public HandleList(ArrayList<String> movieList,
                      ArrayList<String> userList){
        this.movieList = movieList;
        this.userList = userList;
    }

    public float getAverageSum(){
        if(movieIdList==null) setMovieIdList();
        if(userIdList==null) setUserIdList();
        if(ratingList==null) setRatingList();

        ArrayList<String> filtered_rating = new ArrayList<>();
        ArrayList<String> filtered_userid = new ArrayList<>();

        /* filter rating list by movie genre first */
        for(String rateLine : ratingList){
            String[] parseLine = rateLine.split("::");
            for (String movieId : movieIdList) {
                if (parseLine[1].equals(movieId)) {
                    filtered_userid.add(parseLine[0]);
                    filtered_rating.add(parseLine[2]);
                }
            }
        }

        int index = 0;
        int count = 0;
        float averageSum = 0;

        /* filter rating list by userId, get average sum */
        for(String filtered_user : filtered_userid){
            for(String user : userIdList){
                if (filtered_user.equals(user)){
                    averageSum += Integer.parseInt(filtered_rating.get(index));
                    count++;
                }
            }
            index++;
        }
        averageSum /= count;
        return averageSum;
    }

    /* Set ArrayList that only has movieId */
    public void setMovieIdList(){
        this.movieIdList = new ArrayList<String>();
        for(String movie : this.movieList){
            String temp = movie.split("::")[0];
            this.movieIdList.add(temp);
        }
    }

    /* Set ArrayList that only has userId */
    public void setUserIdList(){
        this.userIdList = new ArrayList<String>();
        for(String user : this.userList){
            String temp = user.split("::")[0];
            this.userIdList.add(temp);
        }
    }

    /* Set RatingList from file */
    public void setRatingList() {
        this.ratingList = new ArrayList<String>();
        File file = new File("./data/ratings.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                ratingList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMovieList(){
        return movieList;
    }
    public ArrayList<String> getUserList(){
        return userList;
    }
    public ArrayList<String> getMovieIdList(){
        if(movieIdList==null) setMovieIdList();
        return movieIdList;
    }
    public ArrayList<String> getUserIdList(){
        if(userIdList==null) setUserIdList();
        return userIdList;
    }
    public ArrayList<String> getRatingList(){
        if(ratingList==null) setRatingList();
        return userIdList;
    }


}
