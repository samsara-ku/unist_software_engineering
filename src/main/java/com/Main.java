package com;

import data.Load;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    // Check whether the arguments are two or not
    if (args.length != 2) {
      System.out.println("The number of arguments is not appropriate. Please use only 2 parameters, Categories and Occupation. (e.g Adventure Educator)");
      return;
    }

    Load Load = new Load(args[0], args[1]);
  }
}