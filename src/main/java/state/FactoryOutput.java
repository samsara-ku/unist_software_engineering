package state;

import java.util.ArrayList;
import java.util.Arrays;

public interface FactoryOutput {

  ArrayList<String> occupation_list = new ArrayList<>(Arrays.asList(
      "Academic", "Educator", "Artist", "Clerical",
      "Admin", "College", "Gradstudent", "Custormerservice", "Doctor", "Healthcare", "Executive",
      "Managerial", "Farmer", "Homemaker", "K-12student", "Lawyer", "Programmer", "Retired",
      "Sales", "Marketing", "Scientist", "Self-employed", "Technician", "Engineer", "Tradesman",
      "Craftsman", "Unemployed", "Writer"));

  ArrayList<String> category_list = new ArrayList<>(Arrays.asList(
      "Action", "Adventure", "Animation", "Children's",
      "Comedy", "Crime", "Documentary", "Drama", "Fantasy", "Film-noir", "Horror", "Musical",
      "Mystery", "Romance", "Sci-fi", "Thriller", "War", "Western"));

  void getResult();
}
