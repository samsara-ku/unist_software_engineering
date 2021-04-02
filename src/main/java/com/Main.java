package com;

import data.Load;
import handlelist.HandleList;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    String[] occupation_list = new String[]{"Academic", "Educator", "Artist", "Clerical",
        "Admin", "College", "Gradstudent", "Custormerservice", "Doctor", "Healthcare", "Executive",
        "Managerial", "Farmer", "Homemaker", "K-12student", "Lawyer", "Programmer", "Retired",
        "Sales", "Marketing", "Scientist", "Self-employed", "Technician", "Engineer", "Tradesman",
        "Craftsman", "Unemployed", "Writer"};

    String[] category_list = new String[]{"Action", "Adventure", "Animation", "Children's",
        "Comedy", "Crime", "Documentary", "Drama", "Fantasy", "Film-noir", "Horror", "Musical",
        "Mystery", "Romance", "Sci-fi", "Thriller", "War", "Western"};

    // Check whether the arguments are two or not
    if (args.length != 2) {
      System.out.println(
          "The number of arguments is not appropriate. Please use only 2 parameters, Categories and Occupation. (e.g Adventure Educator)");
      return;
    }

    // Safe for number of argument parsing
    Load data = new Load(args[0], args[1]);
    HandleList process = new HandleList(data.getMovieList(), data.getUserList());

    int movieCount = data.movieListLength();
    double result = process.getAverageRating();
    boolean hasProperCategories = data.hasContained(category_list, data.getCategories());
    boolean hasProperOccupation = data.hasContained(occupation_list, data.getOccupation());

    if (!hasProperCategories && !hasProperOccupation) {
      System.out
          .println(String.format(
              "Can't search because there are inappropriate category and occupation. Please try again with appropriate category and occupation.",
              data.getOccupation()));
      return;
    } else if (!hasProperCategories) {
      System.out
          .println("Can't search because there is inappropriate category. Please try again with appropriate category.");
      return;
    } else if (!hasProperOccupation) {
      System.out.println(String.format(
          "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.",
          data.getOccupation()));
      return;
    } else {
      if (movieCount == 0) {
        System.out.println(
            "Despite the correct category and occupation, nothing was found. Please try again with different category.");
        return;
      }

      if (result == 0) {
        System.out.println(String.format(
            "There are a total of \"%d\" movies that fit the requested category, but the average score is 0 points due to no rating data. Please try again with another occupation.",
            movieCount));
        return;
      }

      // Result for searching and calculating avg. score
      System.out.println(String.format(
          "There are a total of \"%d\" movies that fit the requested category, and the average score is about \"%.2f\" points.",
          movieCount, result));
    }
  }
}