package handlelist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import data.Load;
import java.util.ArrayList;
import org.junit.Test;

public class HandleLIstTest {

  @Test
  public void testListGetters() {
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());

    assertNotNull("movieList is NULL", handleList.getMovieList());
    System.out.println("passed test get movieList");

    assertNotNull("userList is NULL", handleList.getUserList());
    System.out.println("passed test get userList");

    assertNotNull("userIdList is NULL", handleList.getUserIdList());
    System.out.println("passed test getUserIdList");

    assertNotNull("movieIdList is NULL", handleList.getMovieIdList());
    System.out.println("passed test getMovieIdList");

    assertNotNull("ratingList is NULL", handleList.getRatingList());
    System.out.println("passed test getRatingList");
  }

  @Test
  public void testListSetters() {
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());

    handleList.setRequiredLists();

    assertNotNull("userIdList is NULL", handleList.getUserIdList());
    System.out.println("passed test setUserIdList");

    assertNotNull("movieIdList is NULL", handleList.getMovieIdList());
    System.out.println("passed test setMovieIdList");

    assertNotNull("ratingList is NULL", handleList.getRatingList());
    System.out.println("passed test setRatingList");

    ArrayList<String> userIdList = handleList.getUserIdList();
    ArrayList<String> movieIdList = handleList.getMovieIdList();
    ArrayList<String> ratingList = handleList.getRatingList();

    handleList.setRequiredLists();

    assertTrue("userIdList changed", userIdList.equals(handleList.getUserIdList()));
    System.out.println("passed test repeat setUserIdList");

    assertTrue("movieList changed", movieIdList.equals(handleList.getMovieIdList()));
    System.out.println("passed test repeat setMovieIdList");

    assertTrue("ratingList changed", ratingList.equals(handleList.getRatingList()));
    System.out.println("passed test repeat setRatingList");
  }

  @Test
  public void testGetAllMovieListSize() {
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    int movieListSize = handleList.getAllMovieListSize();

    assertNotNull("All movieList size is NULL", movieListSize);
    System.out
        .println(String.format("All movieList size is \"%d\"", movieListSize));
  }

  @Test
  public void testGetAverageRating() {
    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    double averageRating = handleList.getAverageRating();

    assertNotNull("AverageRating is NULL", averageRating);
    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));
  }

  @Test
  public void testSomeArgues() {
    double averageRating;

    Load data = new Load("comedy", "unemployed");
    HandleList handleList = new HandleList(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new Load("comedy|romance", "artist");
    handleList = new HandleList(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new Load("comedy|romance|adventure", "lawyer");
    handleList = new HandleList(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new Load("comedy|romance|adventure|sci-fi|horror", "farmer");
    handleList = new HandleList(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));
  }
}
