package com;

import data.LoadLink;
import data.LoadUser;
<<<<<<< HEAD
import handlelist.BestMovie;
=======
import handlelist.SimilarUserRecommend;
import handlelist.AverageRating;
>>>>>>> origin
import java.io.IOException;
import java.util.ArrayList;
import state.TwoArgs;

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
      TwoArgs state = new TwoArgs(args[0], args[1]);

      state.getResult();
    }

    // Milestone2 Part1 - 3 arguments : "gender", "age", "occupation"
    else if (args.length == 3) {

      // Gender input error handling
      if (!(args[0].equals("F") || (args[0].equals("M")) || (args[0].isEmpty()))) {
        System.out.println(
            "Wrong gender input. Please try again with F for female or M for male.");
        return;
      }

      // Age input error handling
      if (!args[1].isEmpty()) {
        try {
          int age = Integer.parseInt(args[1]);
          if (age <= 0) {
            System.out.println(
                "Wrong age input. (Non-positive age) Please try again with appropriate age.");
            return;
          }
        } catch (Exception e) {
          System.out.println(
              "Wrong age input. (Not an integer) Please try again with appropriate age.");
          return;
        }
      }

      LoadUser data = new LoadUser(args[0], args[1], args[2]);

      // Occupation input error handling
      boolean hasProperOccupation = data.hasContained(occupation_list, data.getOccupation());

      if (!(hasProperOccupation || args[2].isEmpty())) {
        System.out.println(String.format(
            "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.",
            data.getOccupation()));
        return;
      }

      SimilarUserRecommend best = new SimilarUserRecommend(data.getUserList());

      LoadLink result = new LoadLink(best.getTop10());

      ArrayList<String> top10 = result.getLinkList();

      int idx = 0;

      for (String line : top10) {
        String name = line.split("/")[0];
        String link = line.split("/")[1];
        int number = best.getTop10_num().get(idx);
        double rating = best.getTop10_rat().get(idx);
        idx = idx + 1;
        System.out.println(String
            .format("%d. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.",
                idx, name, link, number, rating));
      }

    }

    // Milestone2 Part2 - 4 arguments : "gender", "age", "occupation", "genre"
    else if (args.length == 4) {

      // Gender input error handling
      if (!(args[0].equals("F") || (args[0].equals("M") || (args[0].isEmpty())))) {
        System.out.println(
            "Wrong gender input. Please try again with F for female or M for male.");
        return;
      }

      // Age input error handling
      if (!args[1].isEmpty()) {
        try {
          int age = Integer.parseInt(args[1]);
          if (age <= 0) {
            System.out.println(
                "Wrong age input. (Non-positive age) Please try again with appropriate age.");
            return;
          }
        } catch (Exception e) {
          System.out.println(
              "Wrong age input. (Not an integer) Please try again with appropriate age.");
          return;
        }
      }

      boolean genrePass = args[3].equals("");
      LoadUser data = new LoadUser(args[0], args[1], args[2]);

      // Occupation input error handling
      boolean hasProperOccupation = data.hasContained(occupation_list, data.getOccupation());

      if (!(hasProperOccupation || args[2].isEmpty())) {
        System.out.println(String.format(
            "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.",
            data.getOccupation()));
        return;
      }

      // Genre input error handling
      String[] inputCategory = args[3].split("\\|");

      int count = 0;

      for (String input : inputCategory) {
        for (String truthValue : category_list) {
          if (input.equalsIgnoreCase(truthValue)) {
            count++;
          }
        }
      }

      boolean valid_category = (inputCategory.length == count);

      if (!(genrePass || valid_category)) {
        System.out
            .println(
                "Can't search because there is inappropriate category. Please try again with appropriate category.");
        return;
      }

      SimilarUserRecommend best;
      if (genrePass == true) {
        best = new SimilarUserRecommend(data.getUserList());
      } else {
        best = new SimilarUserRecommend(data.getUserList(), args[3]);
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
        System.out.println(String
            .format("%d. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.",
                idx, name, link, number, rating));
      }

    } else {
      System.out.println(
          "The number of arguments is not appropriate. Please use 2, 3, or 4 parameters. (Categories, Occupation | Gender, Age, Occupation | Gender, Age, Occupation, Genre)");
      return;
    }
  }
}