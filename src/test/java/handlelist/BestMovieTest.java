package handlelist;

import static org.junit.Assert.assertEquals;

import data.LoadUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.junit.Test;

public class BestMovieTest {
  //test without genre

  //Test getFileListSize method if it get correct length of users.dat file and movies.dat file
  @Test
  public void testGetFileListSize() {
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList());

    assertEquals("userListSize is incorrect", 6040, bestMovie.getFileListSize("./data/users.dat"));
    System.out.println("passed test getUserListSize");

    assertEquals("movieListSize is incorrect", 3952, bestMovie.getFileListSize("./data/movies.dat"));
    System.out.println("passed test getMovieListSize");
  }

  //Test setUserIdIndexList method if it set all of rating data and work correctly
  @Test
  public void testSetUserIdIndexList(){
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList());
    HashMap<String ,Integer>[] userIdIndexList = bestMovie.getUserIdIndexList();
    int size = 0;

    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    int allRatingNum = 1000209;

    assertEquals("UserIdIndexList does not add all ratings", size, allRatingNum);

    //randomly choose 3 elements and check
    Random rand = new Random();
    for(int i=0; i<3; i++){
      int index = rand.nextInt(rand.nextInt(bestMovie.getFileListSize("./data/movies.dat")));
      for(HashMap.Entry<String, Integer> userRating: userIdIndexList[index].entrySet()){
        System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
      }
      System.out.println();
    }

    System.out.println("passed test setUserIdIndexList");
  }

  //Test setTop10 method if result is changed according to input
  @Test
  public void testSetTop10(){
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList());

    ArrayList<Integer> top10 = bestMovie.getTop10();
    ArrayList<Integer> top10Num = bestMovie.getTop10_num();
    ArrayList<Double> top10Rat = bestMovie.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    LoadUser data2 = new LoadUser("F","","");
    BestMovie bestMovie2 = new BestMovie(data2.getUserList());

    top10 = bestMovie2.getTop10();
    top10Num = bestMovie2.getTop10_num();
    top10Rat = bestMovie2.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    LoadUser data3 = new LoadUser("M","22","");
    BestMovie bestMovie3 = new BestMovie(data3.getUserList());

    top10 = bestMovie3.getTop10();
    top10Num = bestMovie3.getTop10_num();
    top10Rat = bestMovie3.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    LoadUser data4 = new LoadUser("F","32","college");
    BestMovie bestMovie4 = new BestMovie(data4.getUserList());

    top10 = bestMovie4.getTop10();
    top10Num = bestMovie4.getTop10_num();
    top10Rat = bestMovie4.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
  }

  //Test with genre

  //Test parseGenre to work correctly
  @Test
  public void testParseGenre(){
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList());

    String[] parse1 = bestMovie.parseGenre("Adventure");
    for(String genre : parse1){
      assertEquals("parsed genre changed",genre, "Adventure");
    }

    //compare with correct output
    String[] parse2 = bestMovie.parseGenre("adventure|Comedy|romance");
    String[] expected = {"adventure","Comedy","romance"};
    int i = 0;
    for(String genre : parse2){
      assertEquals("parsed genre changed",genre, expected[i++]);
    }
    System.out.println("passed test parseGenre");
  }

  //Test setMovieGenreList to work correctly
  @Test
  public void testSetMovieGenreList() {
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList(),"romance");
    Random rand = new Random();

    //randomly select 10 movies and check correct
    for(int i=0; i<10; i++){
      String index = Integer.toString(rand.nextInt(bestMovie.getFileListSize("./data/movies.dat")));
      if(bestMovie.getMovieGenreList().get(index) != null) {
        System.out.print(index + " ");
        for (String genre : bestMovie.getMovieGenreList().get(index)) {
          System.out.print(genre + " ");
        }
        System.out.println();
      }
    }
  }

  //Test setUserIdIndexList method if it set different data according to genre
    @Test
  public void testSetUserIdIndexListGenre(){
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList(),"romance");

    HashMap<String ,Integer>[] userIdIndexList = bestMovie.getUserIdIndexList();
    int size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    System.out.printf("romance size : %d \n",size);

    //check first user's data
    for(HashMap.Entry<String, Integer> userRating: userIdIndexList[0].entrySet()){
      System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
    }
    System.out.println();

    BestMovie bestMovie2 = new BestMovie(data.getUserList(),"comedy|romance");

    userIdIndexList = bestMovie2.getUserIdIndexList();
    size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    System.out.printf("comedy|romance size : %d \n",size);

    for(HashMap.Entry<String, Integer> userRating: userIdIndexList[0].entrySet()){
      System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
    }
    System.out.println();


    BestMovie bestMovie3 = new BestMovie(data.getUserList(),"comedy|romance|adventure");

    userIdIndexList = bestMovie3.getUserIdIndexList();
    size = 0;
    for(HashMap<String, Integer> userId : userIdIndexList){
      size += userId.size();
    }
    System.out.printf("comedy|romance|adventure size : %d \n",size);

    for(HashMap.Entry<String, Integer> userRating: userIdIndexList[0].entrySet()){
      System.out.printf("%s %d ", userRating.getKey(), userRating.getValue());
    }
    System.out.println();


    System.out.println("passed test setUserIdIndexList with genre");
  }

  //Test setTop10 method if it set different data according to genre
  @Test
  public void testSetTop10WithGenre(){
    LoadUser data = new LoadUser("","","");
    BestMovie bestMovie = new BestMovie(data.getUserList(),"adventure");

    ArrayList<Integer> top10 = bestMovie.getTop10();
    ArrayList<Integer> top10Num = bestMovie.getTop10_num();
    ArrayList<Double> top10Rat = bestMovie.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    BestMovie bestMovie2 = new BestMovie(data.getUserList(),"comedy");

    top10 = bestMovie2.getTop10();
    top10Num = bestMovie2.getTop10_num();
    top10Rat = bestMovie2.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();

    BestMovie bestMovie3 = new BestMovie(data.getUserList(),"romance|comedy");

    top10 = bestMovie3.getTop10();
    top10Num = bestMovie3.getTop10_num();
    top10Rat = bestMovie3.getTop10_rat();

    for(int i = 0; i<10; i++){
      System.out.printf("%d %d %.2f\n", top10.get(i), top10Num.get(i), top10Rat.get(i));
    }
    System.out.println();
  }
 }

