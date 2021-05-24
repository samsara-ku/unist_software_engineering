package factory.rating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import factory.user.LoadMoreThanTwoArgs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

public class RecommendMovieAbstractTest {

  @Test
  public void testSortWithRating() {

    LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
    RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList());

    HashMap<Integer, Double> testmap = new HashMap<>();
    testmap.put(1, 2.2);
    testmap.put(2, 3.3);
    testmap.put(3, 4.4);

    Entry<Integer, Double> result = actualRating.sortWithRating(testmap).get(0);

    assertEquals(Double.valueOf(4.4), result.getValue());
    System.out.println("passed RecommendMovieAbstract_sortWithRating");
  }

  @Test
  public void testSortGetTopN() {

    LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
    RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList());

    HashMap<Integer, Integer> testmap = new HashMap<>();
    testmap.put(1, 3);
    testmap.put(2, 4);
    testmap.put(3, 10);
    testmap.put(4, 5);
    testmap.put(5, 7);

    int result = actualRating.sortGetTopN(testmap, 3).get(0);

    assertEquals(3, result);

    System.out.println("passed RecommendMovieAbstract_sortGetTopN");
  }
}