package handlelist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import data.Load;
import java.util.ArrayList;
import org.junit.Test;

public class AverageRatingTest {

  @Test
  public void testListGetters() {
    Load data = new Load("comedy", "unemployed");
    AverageRating averageRating = new AverageRating(data.getMovieList(), data.getUserList());


    assertNotNull("userIdList is NULL", averageRating.getUserIdList());
    System.out.println("passed test getUserIdList");

    assertNotNull("movieIdList is NULL", averageRating.getMovieIdList());
    System.out.println("passed test getMovieIdList");

  }

  @Test
  public void testListSetters() {
    Load data = new Load("comedy", "unemployed");
    AverageRating averageRating = new AverageRating(data.getMovieList(), data.getUserList());

    averageRating.setIdLists();

    assertNotNull("userIdList is NULL", averageRating.getUserIdList());
    System.out.println("passed test setUserIdList");

    assertNotNull("movieIdList is NULL", averageRating.getMovieIdList());
    System.out.println("passed test setMovieIdList");


    ArrayList<String> userIdList = averageRating.getUserIdList();
    ArrayList<String> movieIdList = averageRating.getMovieIdList();

    averageRating.setIdLists();

    assertTrue("userIdList changed", userIdList.equals(averageRating.getUserIdList()));
    System.out.println("passed test repeat setUserIdList");

    assertTrue("movieList changed", movieIdList.equals(averageRating.getMovieIdList()));
    System.out.println("passed test repeat setMovieIdList");

  }


  @Test
  public void testGetAverageRating() {
    Load data = new Load("comedy", "unemployed");
    AverageRating handleList = new AverageRating(data.getMovieList(), data.getUserList());
    double averageRating = handleList.getAverageRating();

    assertNotNull("AverageRating is NULL", averageRating);
    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));
  }

  @Test
  public void testSomeArgues() {
    double averageRating;

    Load data = new Load("comedy", "unemployed");
    AverageRating handleList = new AverageRating(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new Load("comedy|romance", "artist");
    handleList = new AverageRating(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new Load("comedy|romance|adventure", "lawyer");
    handleList = new AverageRating(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new Load("comedy|romance|adventure|sci-fi|horror", "farmer");
    handleList = new AverageRating(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out
        .println(String.format("The average rating is \"%.2f\"", averageRating));
  }
}
