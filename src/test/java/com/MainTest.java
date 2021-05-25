package com;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MainTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final PrintStream originalOut = System.out;

  @BeforeAll
  public static void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterAll
  public static void restoreStreams() {
    System.setOut(originalOut);
    System.out.println(outContent.toString());
  }

  //check with correct inputs*/
  @Test
  public void testMainCorrectInput() {
    try {
      String[] userInput = {"F", "30", "gradstudent"};
      Main.main(userInput);
      System.out.print("passed test correct input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

