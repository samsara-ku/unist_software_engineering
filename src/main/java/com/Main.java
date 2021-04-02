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

    boolean hasProperCategories = data.hasContained(category_list, data.getCategories());
    boolean hasProperOccupation = data.hasContained(occupation_list, data.getOccupation());

    if (!hasProperCategories && !hasProperOccupation) {
      System.out
          .println(String.format(
              "No results were found for the movie category and occupation \"%s\" you requested. Please try again.",
              data.getOccupation()));
      return;
    } else if (!hasProperCategories) {
      System.out
          .println("No results were found for the movie category you requested. Please try again.");
      return;
    } else if (!hasProperOccupation) {
      System.out.println(String.format(
          "No results were found for the occupation \"%s\" you requested. Please try again.",
          data.getOccupation()));
      return;
    } else {
      // Result for searching and calculating avg. score
      System.out.println(String.format(
          "There are a total of \"%d\" movies that fit the requested category, and the average score is about \"%.2f\" points.",
          data.movieListLength(), process.getAverageRating()));
    }
  }
}