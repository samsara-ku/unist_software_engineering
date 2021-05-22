package factory;

import static org.junit.Assert.assertTrue;
import java.io.IOException;

// cover branch of milestone2
public class FourArgsFactoryTest {

    @Test
    public void testNegativeAgeCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "-1", "gradstudent", "comedy");

        // invalid age input (non-positive)
        String[] input = {"F", "-1", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Non-positive age) Please try again with appropriate age."));

        System.out.println("passed FourArgsFactory_NegativeAgeCheckValidity");
    }

    @Test
    public void testNonIntegerAgeCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "s", "gradstudent", "comedy");

        // invalid age input (not an integer)
        String[] input = {"F", "s", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Not an integer) Please try again with appropriate age."));

        System.out.println("passed FourArgsFactory_NonIntegerAgeCheckValidity");
    }

    @Test
    public void testGenderCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("D", "32", "gradstudent", "comedy");

        // invalid gender input
        String[] input = {"D", "32", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong gender input. Please try again with F for female or M for male."));

        System.out.println("passed FourArgsFactory_GenderCheckValidity");
    }

    @Test
    public void testOccupationCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "32", "gradstudents", "comedy");

        // invalid occupation input
        String[] input = {"F", "32", "gradstudents", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Can't search inappropriate occupation, gradstudents. Please try again with appropriate occupation.%n"));

        System.out.println("passed FourArgsFactory_OccupationCheckValidity");
    }

    @Test
    public void testGenreCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "32", "gradstudents", "comed");

        // invalid occupation input
        String[] input = {"F", "32", "gradstudents", "comed"};
        assertTrue(actualInput.checkValidity(input).equals("Can't search because there is inappropriate category. Please try again with appropriate category."));

        System.out.println("passed FourArgsFactory_OccupationCheckValidity");
    }
}
