package factory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MovieRecommendFactoryTest {

  @Test
  public void testTitleIsValid() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Stroy", "5");

    assertTrue("titleIsValid doesn't work", actualInput.titleIsValid() == false);

    System.out.println("passed MovieRecommendFactory_titleIsValid");
  }

  @Test
  public void testLimitIsValid() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story", "-1");

    assertTrue("limitIsValid doesn't work", actualInput.limitIsValid() == false);

    System.out.println("passed MovieRecommendFactory_limitIsValid");
  }

  @Test
  public void testgetErrorMessage() {

    // w/o invalid input
    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story", "5");

    assertEquals("", actualInput.getErrorMessage());

    System.out.println("passed MovieRecommendFactory_getErrorMessage");
  }
}
