package factory.rating;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class RatingUtilsTest {

  @Test
  public void RatingUtilsTest() {
    RatingUtils actualInput = new RatingUtils();

    int userSize = actualInput.getFileListSize("./data/users.dat");
    assertEquals(userSize, actualInput.setUserIdIndexList().length);
    System.out.println("passed RatingUtils_setUserIdIndexList1");

    String[] testArr1 = {"drama", "action"};
    String[] testArr2 = {"drama"};
    assertTrue(userSize >= actualInput.setUserIdIndexList(testArr1).length);
    assertTrue(actualInput.setUserIdIndexList(testArr1).length >= actualInput
        .setUserIdIndexList(testArr2).length);
    System.out.println("passed RatingUtils_setUserIdIndexList2");
  }

  @Test
  public void TestGet() {
    RatingUtils actualInput = new RatingUtils();

    ArrayList<String> testList = new ArrayList<>();
    assertNotNull(actualInput.getIdList(testList));
    System.out.println("passed RatingUtils_getIdList");

    assertNotNull(actualInput.getRatingList());
    System.out.println("passed RatingUtils_getRatingList");

    assertEquals(6040, actualInput.getFileListSize("./data/users.dat"));
    System.out.println("passed RatingUtils_getFileListSize");

    assertNotNull(actualInput.getMovieGenreList());
    System.out.println("passed RatingUtils_getMovieGenreList");

    String[] testArr = {"drama", "action"};
    assertNotNull(actualInput.parseGenre("drama|action"));
    assertArrayEquals(testArr, actualInput.parseGenre("drama|action"));
    System.out.println("passed RatingUtils_getRatingList");

    assertNotNull(actualInput.getMovieIdIndexList());
    System.out.println("passed RatingUtils_getMovieIdIndexList");
  }
}