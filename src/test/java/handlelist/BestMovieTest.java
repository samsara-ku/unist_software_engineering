package handlelist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import data.Load;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;

public class BestMovieTest {
  //test without genre
  @Test
  public void testGetSizeMethods() {
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    BestMovie bestMovie = new BestMovie(handleList.getUserIdList());

    assertNotNull("userListSize is NULL", bestMovie.getAllUserListSize());
    System.out.println("passed test get userList");

    assertNotNull("movieListSize is NULL", bestMovie.getAllMovieListSize());
    System.out.println("passed test getUserIdList");
  }

  @Test
  public void testSetUserIdIndexList(){
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    BestMovie bestMovie = new BestMovie(handleList.getUserIdList());

    HashMap<String ,Integer>[] userIdIndexList = bestMovie.getUserIdIndexList();
    int size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    int allRatingNum = 1000209;

    assertEquals(size, allRatingNum);
    System.out.println("passed test setUserIdIndexList");
  }

  @Test
  public void testSetTop10(){
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    BestMovie bestMovie = new BestMovie(handleList.getUserIdList());

    ArrayList<Integer> top10 = bestMovie.getTop10();
    ArrayList<Integer> top10Num = bestMovie.getTop10_num();
    ArrayList<Double> top10Rat = bestMovie.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.println(String.format("\"%d\" \"%d\" \"%.2f\"", top10.get(i), top10Num.get(i), top10Rat.get(i)));
    }
  }

  //Test with genre
  @Test
  public void testParseGenre(){
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    BestMovie bestMovie = new BestMovie(handleList.getUserIdList());

    String[] parse1 = bestMovie.parseGenre("Adventure");
    for(String genre : parse1){
      assertEquals("parsed genre changed",genre, "Adventure");
    }

    String[] parse2 = bestMovie.parseGenre("adventure|Comedy|romance");
    String[] expected = {"adventure","Comedy","romance"};
    int i = 0;
    for(String genre : parse2){
      assertEquals("parsed genre changed",genre, expected[i++]);
    }
    System.out.println("passed test parseGenre");
  }

  @Test
  public void testSetUserIdIndexListGenre(){
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    BestMovie bestMovie = new BestMovie(handleList.getUserIdList(),"romance");

    HashMap<String ,Integer>[] userIdIndexList = bestMovie.getUserIdIndexList();
    int size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    System.out.println(size);

    BestMovie bestMovie2 = new BestMovie(handleList.getUserIdList(),"comedy|romance|adventure");

    userIdIndexList = bestMovie2.getUserIdIndexList();
    size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    System.out.println(size);

    BestMovie bestMovie3 = new BestMovie(handleList.getUserIdList(),"comedy|romance|adventure");

    userIdIndexList = bestMovie3.getUserIdIndexList();
    size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    System.out.println(size);
    System.out.println("passed test setUserIdIndexList with genre");
  }


  @Test
  public void testSetTop10Genre(){
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    BestMovie bestMovie = new BestMovie(handleList.getUserIdList(),"adventure");

    ArrayList<Integer> top10 = bestMovie.getTop10();
    ArrayList<Integer> top10Num = bestMovie.getTop10_num();
    ArrayList<Double> top10Rat = bestMovie.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.println(String.format("\"%d\" \"%d\" \"%.2f\"", top10.get(i), top10Num.get(i), top10Rat.get(i)));
    }
    BestMovie bestMovie2 = new BestMovie(handleList.getUserIdList(),"adventure|sci-fi");

    top10 = bestMovie2.getTop10();
    top10Num = bestMovie2.getTop10_num();
    top10Rat = bestMovie2.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.println(String.format("\"%d\" \"%d\" \"%.2f\"", top10.get(i), top10Num.get(i), top10Rat.get(i)));
    }

    BestMovie bestMovie3 = new BestMovie(handleList.getUserIdList(),"adventure|sci-fi|comedy");

    top10 = bestMovie3.getTop10();
    top10Num = bestMovie3.getTop10_num();
    top10Rat = bestMovie3.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.println(String.format("\"%d\" \"%d\" \"%.2f\"", top10.get(i), top10Num.get(i), top10Rat.get(i)));
    }
  }

  }

