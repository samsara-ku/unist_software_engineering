package handlelist;

import static org.junit.Assert.assertEquals;

import data.LoadUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.junit.Test;

public class SimilarUserRecommendTest {
  //test without genre

  //Test setUserIdIndexList method if it set all of rating data and work correctly
  @Test
  public void testSetUserIdIndexList() {
    LoadUser data = new LoadUser("", "", "");
    SimilarUserRecommend similarUserRecommend = new SimilarUserRecommend(data.getUserList());
    HashMap<String, Integer>[] userIdIndexList = similarUserRecommend.getUserIdIndexList();
    int size = 0;
    int printSize = 0;

    for (HashMap<String, Integer> userId : userIdIndexList) {
      size += userId.size();
    }
    int allRatingNum = 1000209;

    assertEquals("UserIdIndexList does not add all ratings", size, allRatingNum);

    //randomly choose 3 elements and check
    Random rand = new Random();
    for (int i = 0; i < 3; i++) {
      int index = rand.nextInt(rand.nextInt(similarUserRecommend.handleList.getFileListSize("./data/movies.dat")));
      for (HashMap.Entry<String, Integer> userRating : userIdIndexList[index].entrySet()) {
        System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
        printSize++;
        if (printSize > 5) {
          printSize = 0;
          break;
        }
      }
      System.out.println();
    }

    System.out.println("passed test setUserIdIndexList");
  }

  //Test setTop10 method if result is changed according to input
  @Test
  public void testSetTop10() {
    LoadUser data = new LoadUser("", "", "");
    SimilarUserRecommend similarUserRecommend = new SimilarUserRecommend(data.getUserList());

    ArrayList<Integer> top10 = similarUserRecommend.getTop10();
    ArrayList<Integer> top10Num = similarUserRecommend.getTop10_num();
    ArrayList<Double> top10Rat = similarUserRecommend.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    LoadUser data2 = new LoadUser("F", "", "");
    SimilarUserRecommend similarUserRecommend2 = new SimilarUserRecommend(data2.getUserList());

    top10 = similarUserRecommend2.getTop10();
    top10Num = similarUserRecommend2.getTop10_num();
    top10Rat = similarUserRecommend2.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    LoadUser data3 = new LoadUser("M", "22", "");
    SimilarUserRecommend similarUserRecommend3 = new SimilarUserRecommend(data3.getUserList());

    top10 = similarUserRecommend3.getTop10();
    top10Num = similarUserRecommend3.getTop10_num();
    top10Rat = similarUserRecommend3.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    LoadUser data4 = new LoadUser("F", "32", "college");
    SimilarUserRecommend similarUserRecommend4 = new SimilarUserRecommend(data4.getUserList());

    top10 = similarUserRecommend4.getTop10();
    top10Num = similarUserRecommend4.getTop10_num();
    top10Rat = similarUserRecommend4.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
  }

  //Test with genre

  //Test parseGenre to work correctly
  @Test
  public void testParseGenre() {
    LoadUser data = new LoadUser("", "", "");
    SimilarUserRecommend similarUserRecommend = new SimilarUserRecommend(data.getUserList());

    String[] parse1 = similarUserRecommend.handleList.parseGenre("Adventure");
    for (String genre : parse1) {
      assertEquals("parsed genre changed", genre, "Adventure");
    }

    //compare with correct output
    String[] parse2 = similarUserRecommend.handleList.parseGenre("adventure|Comedy|romance");
    String[] expected = {"adventure", "Comedy", "romance"};
    int i = 0;
    for (String genre : parse2) {
      assertEquals("parsed genre changed", genre, expected[i++]);
    }
    System.out.println("passed test parseGenre");
  }

  //Test setMovieGenreList to work correctly
  @Test
  public void testSetMovieGenreList() {
    LoadUser data = new LoadUser("", "", "");
    SimilarUserRecommend similarUserRecommend = new SimilarUserRecommend(data.getUserList(), "romance");
    Random rand = new Random();

    //randomly select 10 movies and check correct
    /*for (int i = 0; i < 10; i++) {
      String index = Integer.toString(rand.nextInt(similarUserRecommend.handleList.getFileListSize("./data/movies.dat")));
      if (similarUserRecommend.handleList.getMovieGenreList().get(index) != null) {
        System.out.print(index + " ");
        for (String genre : similarUserRecommend.handleList.getMovieGenreList().get(index)) {
          System.out.print(genre + " ");
        }
        System.out.println();
      }
    }*/
  }

  //Test setUserIdIndexList method if it set different data according to genre
  @Test
  public void testSetUserIdIndexListGenre() {
    LoadUser data = new LoadUser("", "", "");
    SimilarUserRecommend similarUserRecommend = new SimilarUserRecommend(data.getUserList(), "romance");
    HashMap<String, Integer>[] userIdIndexList = similarUserRecommend.getUserIdIndexList();
    int size = 0;
    int printSize = 0;

    for (HashMap<String, Integer> userId : userIdIndexList) {
      size += userId.size();
    }
    System.out.printf("romance size : %d \n", size);

    //check first user's data
    for (HashMap.Entry<String, Integer> userRating : userIdIndexList[0].entrySet()) {
      System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
      printSize++;
      if (printSize > 5) {
        printSize = 0;
        break;
      }
    }
    System.out.println();

    SimilarUserRecommend similarUserRecommend2 = new SimilarUserRecommend(data.getUserList(), "comedy|romance");

    userIdIndexList = similarUserRecommend2.getUserIdIndexList();
    size = 0;
    for (HashMap<String, Integer> userId : userIdIndexList) {
      size += userId.size();
    }
    System.out.printf("comedy|romance size : %d \n", size);

    for (HashMap.Entry<String, Integer> userRating : userIdIndexList[0].entrySet()) {
      System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
      printSize++;
      if (printSize > 5) {
        printSize = 0;
        break;
      }
    }
    System.out.println();

    SimilarUserRecommend similarUserRecommend3 = new SimilarUserRecommend(data.getUserList(), "comedy|romance|adventure");

    userIdIndexList = similarUserRecommend3.getUserIdIndexList();
    size = 0;
    for (HashMap<String, Integer> userId : userIdIndexList) {
      size += userId.size();
    }
    System.out.printf("comedy|romance|adventure size : %d \n", size);

    for (HashMap.Entry<String, Integer> userRating : userIdIndexList[0].entrySet()) {
      System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
      printSize++;
      if (printSize > 5) {
        printSize = 0;
        break;
      }
    }
    System.out.println();

    System.out.println("passed test setUserIdIndexList with genre");
  }

  //Test setTop10 method if it set different data according to genre
  @Test
  public void testSetTop10WithGenre() {
    LoadUser data = new LoadUser("", "", "");
    SimilarUserRecommend similarUserRecommend = new SimilarUserRecommend(data.getUserList(), "adventure");

    ArrayList<Integer> top10 = similarUserRecommend.getTop10();
    ArrayList<Integer> top10Num = similarUserRecommend.getTop10_num();
    ArrayList<Double> top10Rat = similarUserRecommend.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    SimilarUserRecommend similarUserRecommend2 = new SimilarUserRecommend(data.getUserList(), "comedy");

    top10 = similarUserRecommend2.getTop10();
    top10Num = similarUserRecommend2.getTop10_num();
    top10Rat = similarUserRecommend2.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    SimilarUserRecommend similarUserRecommend3 = new SimilarUserRecommend(data.getUserList(), "romance|comedy");

    top10 = similarUserRecommend3.getTop10();
    top10Num = similarUserRecommend3.getTop10_num();
    top10Rat = similarUserRecommend3.getTop10_rat();

    for (int i = 0; i < 10; i++) {
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();
  }
}

