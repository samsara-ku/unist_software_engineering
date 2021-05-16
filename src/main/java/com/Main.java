package com;

import java.io.IOException;
import state.FourArgs;
import state.ThreeArgs;
import state.TwoArgs;

public class Main {

  public static void main(String[] args) throws IOException {
    if (args.length == 2) {
      // Milestone1 - 2 arguments : "category", "occupation"
      TwoArgs state = new TwoArgs(args[0], args[1]);
      state.getResult();
    } else if (args.length == 3) {
      // Milestone2 Part1 - 3 arguments : "gender", "age", "occupation"
      ThreeArgs state = new ThreeArgs(args[0], args[1], args[2]);
      state.getResult();
    } else if (args.length == 4) {
      // Milestone2 Part2 - 4 arguments : "gender", "age", "occupation", "genre"
      FourArgs state = new FourArgs(args[0], args[1], args[2], args[3]);
      state.getResult();
    } else {
      System.out.println(
          "The number of arguments is not appropriate. Please use 2, 3, or 4 parameters. (Categories, Occupation | Gender, Age, Occupation | Gender, Age, Occupation, Genre)");
      return;
    }
  }
}