package handlelist;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import data.Load;
import org.junit.Test;

public class HandleListTest {

  @Test
  public void testLoad() {
    Load actualInput = new Load("comedy|action", "unemployed");

    assertNotNull(actualInput.getCategories());
    System.out.println("passed test_setCategories");

    assertNotNull(actualInput.getOccupation());
    System.out.println("passed test_setOccupation");

    assertNotNull(actualInput.getMovieList());
    System.out.println("passed test_setMovieList");

    assertNotNull(actualInput.getUserList());
    System.out.println("passed test_setUserList");

    System.out.println("passed test_Load");
  }

  @Test
  public void testparseCategory() {
    String[] expectedResult = {"comedy", "action"};
    Load expected = new Load("comedy|action", "unemployed");
    String[] actualResult = expected.parseCategory("comedy|action");
    assertArrayEquals(expectedResult, actualResult);
    System.out.println("passed test_parseCategory");
  }

  @Test
  public void testCategories() {
    Load actualInput = new Load("comedy|action", "unemployed");
    String[] expectedResult = {"comedy", "action"};
    assertArrayEquals(actualInput.getCategories(), expectedResult);
    System.out.println("passed test_getCategories");

    int actualResult = 2;
    assertEquals(actualInput.categoriesLength(), 2);
    System.out.println("passed test_CategoriesLength");
  }

  @Test
  public void testOccupation() {
    Load actualInput = new Load("comedy|action", "unemployed");
    assertEquals("unemployed", actualInput.getOccupation());
    System.out.println("passed test_getOccupation");
  }
}
