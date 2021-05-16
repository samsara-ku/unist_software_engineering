package com;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  //get printed strings
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.out.println(outContent.toString());
  }

  //check with correct inputs
  @Test
  public void testMainCorrectInput() {
    try {
      String[] userInput = {"F", "30", "gradstudent"};
      Main.main(userInput);
      String[] userInput2 = {"M", "25", ""};
      Main.main(userInput2);
      String[] userInput3 = {"M", "", "gradstudent"};
      Main.main(userInput3);
      String[] userInput4 = {"", "", ""};
      Main.main(userInput4);
      System.out.print("passed test correct input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with correct inputs include genre
  @Test
  public void testMainCorrectInputWithGenre() {
    try {
      String[] userInput = {"F", "30", "gradstudent", "adventure"};
      Main.main(userInput);
      String[] userInput2 = {"M", "25", "", ""};
      Main.main(userInput2);
      String[] userInput3 = {"M", "", "gradstudent", "adventure"};
      Main.main(userInput3);
      String[] userInput4 = {"", "", "", ""};
      Main.main(userInput4);
      System.out.print("passed test correct input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with invalid gender input
  @Test
  public void testInvalidGender() {
    try {
      String[] userInput = {"InvalidGender", "", ""};
      Main.main(userInput);
      String expectedOutput = "Wrong gender input. Please try again with F for female or M for male.\n";
      assertEquals("fail to check invalid gender", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid gender input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with invalid gender input include gen:wqre
  @Test
  public void testInvalidGenderWithGenre() {
    try {
      String[] userInput = {"InvalidGender", "", "", "adventure"};
      Main.main(userInput);
      String expectedOutput = "Wrong gender input. Please try again with F for female or M for male.\n";
      assertEquals("fail to check invalid gender", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid gender input (include genre)");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with negative age input
  @Test
  public void testNegativeAge() {
    try {
      String[] userInput = {"", "-1", ""};
      Main.main(userInput);
      String expectedOutput = "Wrong age input. (Non-positive age) Please try again with appropriate age.\n";
      assertEquals("fail to check negative age", expectedOutput, this.outContent.toString());
      System.out.print("passed test negative age input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with negative age input include genre
  @Test
  public void testNegativeAgeWithGenre() {
    try {
      String[] userInput = {"", "-1", "", "adventure"};
      Main.main(userInput);
      String expectedOutput = "Wrong age input. (Non-positive age) Please try again with appropriate age.\n";
      assertEquals("fail to check negative age", expectedOutput, this.outContent.toString());
      System.out.print("passed test negative age input (include genre)");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with not num age input
  @Test
  public void testINotNumAge() {
    try {
      String[] userInput = {"", "InvalidAge", ""};
      Main.main(userInput);
      String expectedOutput = "Wrong age input. (Not an integer) Please try again with appropriate age.\n";
      assertEquals("fail to check not-num age", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid not-num age input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with not num age input include genre
  @Test
  public void testINotNumAgeWithGenre() {
    try {
      String[] userInput = {"", "InvalidAge", "", "adventure"};
      Main.main(userInput);
      String expectedOutput = "Wrong age input. (Not an integer) Please try again with appropriate age.\n";
      assertEquals("fail to check not-num age", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid not-num age input (include genre)");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with invalid occupation input
  @Test
  public void testInvalidOccupation() {
    try {
      String[] userInput = {"", "", "InvalidOccupation"};
      Main.main(userInput);
      String expectedOutput = "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.\n";
      assertEquals("fail to check invalid occupation", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid occupation input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with invalid occupation input
  @Test
  public void testInvalidOccupationWithGenre() {
    try {
      String[] userInput = {"", "", "InvalidOccupation", "adventure"};
      Main.main(userInput);
      String expectedOutput = "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.\n";
      assertEquals("fail to check invalid occupation", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid occupation input (include genre)");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with invalid genre input
  @Test
  public void testInvalidGenre() {
    try {
      String[] userInput = {"", "", "", "InvalidGenre|Comedy|Romance"};
      Main.main(userInput);
      String expectedOutput = "Can't search because there is inappropriate category. Please try again with appropriate category.\n";
      assertEquals("fail to check invalid genre", expectedOutput, this.outContent.toString());
      System.out.print("passed test invalid genre input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with less args input
  @Test
  public void testWrongNumArgsLess() {
    try {
      String[] userInput = {"F"};
      Main.main(userInput);
      String expectedOutput = "The number of arguments is not appropriate. Please use 2, 3, or 4 parameters. (Categories, Occupation | Gender, Age, Occupation | Gender, Age, Occupation, Genre)\n";
      assertEquals("fail to check wrong number of args", expectedOutput,
          this.outContent.toString());
      System.out.print("passed test wrong argues input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //check with more args input
  @Test
  public void testWrongNumArgsMore() {
    try {
      String[] userInput = {"F", "23", "gradstudent", "comedy", "wrongArg"};
      Main.main(userInput);
      String expectedOutput = "The number of arguments is not appropriate. Please use 2, 3, or 4 parameters. (Categories, Occupation | Gender, Age, Occupation | Gender, Age, Occupation, Genre)\n";
      assertEquals("fail to check wrong number of args", expectedOutput,
          this.outContent.toString());
      System.out.print("passed test wrong argues input");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  //below tests are for cover branch of milestone1
  @Test
  public void testCorrectInput() {
    try {
      String[] userInput = {"Comedy", "gradstudent"};
      Main.main(userInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testInvalidOccupationM1() {
    try {
      String[] userInput = {"Comedy", "gradstunt"};
      Main.main(userInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testInvalidGenreOccupationM1() {
    try {
      String[] userInput = {"Come", "gradstudent"};
      Main.main(userInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testNoMovieResult() {
    try {
      String[] userInput = {"Comedy|Romance|Horror|sci-fi|adventure|War", "gradstudent"};
      Main.main(userInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

