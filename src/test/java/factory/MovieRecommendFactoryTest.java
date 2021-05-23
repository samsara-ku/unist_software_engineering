package factory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MovieRecommendFactoryTest {

  @Test
  public void testTitleIsValid() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "5");
    assertTrue("titleIsValid doesn't work", actualInput.titleIsValid() == true);

    System.out.println("passed MovieRecommendFactory_testTitleIsValid");
  }

  @Test
  public void testWrongTitle() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Stroy (1995)", "5");

    assertTrue("titleIsValid doesn't work", actualInput.titleIsValid() == false);

    System.out.println("passed MovieRecommendFactory_testWrongTitle");
  }

  @Test
  public void testEmptyTitle() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("", "5");

    assertTrue("titleIsValid doesn't work", actualInput.titleIsValid() == false);

    System.out.println("passed MovieRecommendFactory_testEmptyTitle");
  }

  @Test
  public void testLimitIsValid() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "3");

    assertTrue("limitIsValid doesn't work", actualInput.limitIsValid() == true);

    System.out.println("passed MovieRecommendFactory_limitIsValid");
  }

  @Test
  public void testOutLimit1() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "-1");

    assertTrue("limitIsValid doesn't work", actualInput.limitIsValid() == false);

    System.out.println("passed MovieRecommendFactory_testOutLimit1");
  }

  @Test
  public void testOutLimit2() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "200");

    assertTrue("limitIsValid doesn't work", actualInput.limitIsValid() == false);

    System.out.println("passed MovieRecommendFactory_testOutLimit2");
  }

  @Test
  public void testNotIntLimit() {

    MovieRecommendFactory actualInput = new MovieRecommendFactory("Toy Story (1995)", "s");

    assertTrue("limitIsValid doesn't work", actualInput.limitIsValid() == false);

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
