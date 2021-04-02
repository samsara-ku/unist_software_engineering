package com;

import data.Load;
import handlelist.HandleList;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    // Check whether the arguments are two or not
    if (args.length != 2) {
      System.out.println("The number of arguments is not appropriate. Please use only 2 parameters, Categories and Occupation. (e.g Adventure Educator)");
      return;
    }

    // Safe for number of argument parsing
    Load data = new Load(args[0], args[1]);
    HandleList process = new HandleList(data.getMovieList(), data.getUserList());

    // Result for searching and calculating avg. score
    System.out.println(String.format("There are a total of \"%d\" movies that fit the requested category, and the average score is about \"%.2f\" points.", data.movieListLength(), process.getAverageRating()));
  }
}