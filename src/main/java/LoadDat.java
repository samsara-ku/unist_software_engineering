import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

class Rating {
    //class of the one ratings of Users

    public String InitialLine;
    public int UserId;
    public int MovieId;
    public int Rating;
    public int Timestamp;
        
    Rating(String InitialLine) {
        this.InitialLine = InitialLine;
    }

    public void SetValue() {
		//interpret the Initial input and set the value of the rating

		String Line = InitialLine;
		String[] ValueArr = InitialLine.split("::");
		UserId = Integer.parseInt(ValueArr[0]);
		MovieId = Integer.parseInt(ValueArr[1]);
		Rating = Integer.parseInt(ValueArr[2]);
		Timestamp = Integer.parseInt(ValueArr[3]);
    }
}

class Movie {
        //class of the movie's information

    public String InitialLine;
    public int MovieId;
    public String Title;
    public String[] Genres;

    Movie(String InitialLine) {
        this.InitialLine = InitialLine;
    }

    public void SetValue() {
		//interpret the Initial input and set the info of movie

		String Line = InitialLine;
		String[] ValueArr = InitialLine.split("::");
		MovieId = Integer.parseInt(ValueArr[0]);
		Title = ValueArr[1];
		Genres = ValueArr[2].split("|");
    }
}

class User {
        //class of the user's information

    public String InitialLine;
    public int UserId;
    public String Gender;
    public int Age;
    public int Occupation;
    public int Zip_code;
	
	User(String InitialLine) {
        this.InitialLine = InitialLine;
    }

    public void SetValue() {
		//interpret the Initial input and set the info of user

		String Line = InitialLine;
		String[] ValueArr = InitialLine.split("::");
		UserId = Integer.parseInt(ValueArr[0]);
		Gender = ValueArr[1];
		Age = Integer.parseInt(ValueArr[2]);
		Occupation = Integer.parseInt(ValueArr[3]);
		Zip_code = Integer.parseInt(ValueArr[4]);
    }
}

public class LoadDat {

    public static ArrayList<String> ReadDat(String FileName) {
        // Read FileName.Dat file and return StringList

        ArrayList<String> arrList = new ArrayList<String>();
        try{
            File file = new File(String.format("../../../data/%s", FileName));
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
            arrList.add(line);
            }
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }catch (IOException e) {
            System.out.println(e);
        }
        return arrList;
    }

    public static void PrintStringList(ArrayList<String> StringList) {
        // print and check that we actually read the file properly

        for (int i=0; i<StringList.size(); i++) {
            System.out.println(StringList.get(i));
        }
    }

    public static void main(String[] args) {
        //main function

        ArrayList<String> StringList = LoadDat.ReadDat("movies.dat");
        LoadDat.PrintStringList(StringList);
    }
}