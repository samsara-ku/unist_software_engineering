package handlelist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import factory.rating.RatingWithTwoArgs;
import factory.user.LoadTwoArgs;
import java.util.ArrayList;
import org.junit.Test;

public class AverageRatingTest {

  @Test
  public void testListGetters() {
    LoadTwoArgs data = new LoadTwoArgs("comedy", "unemployed");
    RatingWithTwoArgs averageRating =
        new RatingWithTwoArgs(data.getMovieList(), data.getUserList());

    assertNotNull("userIdList is NULL", averageRating.getUserIdList());
    System.out.println("passed test getUserIdList");

    assertNotNull("movieIdList is NULL", averageRating.getMovieIdList());
    System.out.println("passed test getMovieIdList");
  }

  @Test
  public void testListSetters() {
    LoadTwoArgs data = new LoadTwoArgs("comedy", "unemployed");
    RatingWithTwoArgs averageRating =
        new RatingWithTwoArgs(data.getMovieList(), data.getUserList());

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
    LoadTwoArgs data = new LoadTwoArgs("comedy", "unemployed");
    RatingWithTwoArgs handleList = new RatingWithTwoArgs(data.getMovieList(), data.getUserList());
    double averageRating = handleList.getAverageRating();

    assertNotNull("AverageRating is NULL", averageRating);
    System.out.println(String.format("The average rating is \"%.2f\"", averageRating));
  }

  @Test
  public void testSomeArgues() {
    double averageRating;

    LoadTwoArgs data = new LoadTwoArgs("comedy", "unemployed");
    RatingWithTwoArgs handleList = new RatingWithTwoArgs(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out.println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new LoadTwoArgs("comedy|romance", "artist");
    handleList = new RatingWithTwoArgs(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out.println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new LoadTwoArgs("comedy|romance|adventure", "lawyer");
    handleList = new RatingWithTwoArgs(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out.println(String.format("The average rating is \"%.2f\"", averageRating));

    data = new LoadTwoArgs("comedy|romance|adventure|sci-fi|horror", "farmer");
    handleList = new RatingWithTwoArgs(data.getMovieList(), data.getUserList());
    averageRating = handleList.getAverageRating();

    System.out.println(String.format("The average rating is \"%.2f\"", averageRating));
  }
}
