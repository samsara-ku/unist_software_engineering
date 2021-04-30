package com;

import data.Load;
import data.LoadLink;
import data.LoadUser;
import handlelist.HandleList;
import handlelist.BestMovie;
import java.io.IOException;
import java.util.ArrayList;

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


    // Check the # of arguments

    // Milestone1 - 2 arguments : "category", "occupation"
    if (args.length == 2) {

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

    // Milestone2 Part1 - 3 arguments : "gender", "age", "occupation"
    else if (args.length == 3) {
      LoadUser data = new LoadUser(args[0], args[1], args[2]);
      BestMovie best = new BestMovie(data.getUserList());
      
      LoadLink result = new LoadLink(best.getTop10());

      ArrayList<String> top10 = result.getLinkList();

      int idx = 0;

      for (String line : top10) {
        String name = line.split("/")[0];
        String link = line.split("/")[1];
        int number = best.getTop10_num().get(idx);
        double rating = best.getTop10_rat().get(idx);
        idx = idx + 1;
        System.out.println(String.format("%d. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.", idx, name, link, number, rating));
      }

    }

    // Milestone2 Part2 - 4 arguments : "gender", "age", "occupation", "genre"
    else if (args.length == 4) {
      boolean genrePass = (args[3].equals("")) ? true : false;
      LoadUser data = new LoadUser(args[0], args[1], args[2]);
      BestMovie best;
      if (genrePass == true) {
        best = new BestMovie(data.getUserList());
      }
      else {
        best = new BestMovie(data.getUserList(), args[3]);
      }
      
      LoadLink result = new LoadLink(best.getTop10());

      ArrayList<String> top10 = result.getLinkList();

      int idx = 0;

      for (String line : top10) {
        String name = line.split("/")[0];
        String link = line.split("/")[1];
        int number = best.getTop10_num().get(idx);
        double rating = best.getTop10_rat().get(idx);
        idx = idx + 1;
        System.out.println(String.format("%d. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.", idx, name, link, number, rating));
      }
      
    }

    else {
      System.out.println(
          "The number of arguments is not appropriate. ~~(Need to fix)~~ Please use only 2 parameters, Categories and Occupation. (e.g Adventure Educator)");
      return;
    }
  }
}