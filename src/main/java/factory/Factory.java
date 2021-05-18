package factory;

import java.util.ArrayList;
import java.util.Arrays;

abstract class Factory {

  protected ArrayList<String> occupation_list = new ArrayList<>(Arrays.asList(
      "Academic", "Educator", "Artist", "Clerical",
      "Admin", "College", "Gradstudent", "Custormerservice", "Doctor", "Healthcare", "Executive",
      "Managerial", "Farmer", "Homemaker", "K-12student", "Lawyer", "Programmer", "Retired",
      "Sales", "Marketing", "Scientist", "Self-employed", "Technician", "Engineer", "Tradesman",
      "Craftsman", "Unemployed", "Writer"));

  protected ArrayList<String> category_list = new ArrayList<>(Arrays.asList(
      "Action", "Adventure", "Animation", "Children's",
      "Comedy", "Crime", "Documentary", "Drama", "Fantasy", "Film-noir", "Horror", "Musical",
      "Mystery", "Romance", "Sci-fi", "Thriller", "War", "Western"));

  public abstract void printResult();

  public abstract boolean checkValidity(String[] input);
}
