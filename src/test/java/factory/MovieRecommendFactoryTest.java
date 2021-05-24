package factory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class MovieRecommendFactoryTest {

  @Test
  public void testTitleIsValid() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "5");
    assertTrue(actualInput.titleIsValid() == true, "titleIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_testTitleIsValid");
  }

  @Test
  public void testWrongTitle() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Stroy (1995)", "5");

    assertTrue(actualInput.titleIsValid() == false, "titleIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_testWrongTitle");
  }

  @Test
  public void testEmptyTitle() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("", "5");

    assertTrue(actualInput.titleIsValid() == false, "titleIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_testEmptyTitle");
  }

  @Test
  public void testLimitIsValid() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "3");

    assertTrue(actualInput.limitIsValid() == true, "limitIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_limitIsValid");
  }

  @Test
  public void testOutLimit1() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "-1");

    assertTrue(actualInput.limitIsValid() == false, "limitIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_testOutLimit1");
  }

  @Test
  public void testOutLimit2() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "200");

    assertTrue(actualInput.limitIsValid() == false, "limitIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_testOutLimit2");
  }

  @Test
  public void testNotIntLimit() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "s");

    assertTrue(actualInput.limitIsValid() == false, "limitIsValid doesn't work");

    System.out.println("passed MovieRecommendFactory_testNotIntLimit");
  }

  @Test
  public void testgetErrorMessage() {

    // w/o invalid input
    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "5");

    assertEquals("", actualInput.getErrorMessage());

    System.out.println("passed MovieRecommendFactory_getErrorMessage");
  }

  @Test
  public void testgetResult() {

    // w/o invalid input
    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "5");

    assertNotNull(actualInput.getResult());
    
    System.out.println("passed MovieRecommendFactory_getResult");
  }

  
}
