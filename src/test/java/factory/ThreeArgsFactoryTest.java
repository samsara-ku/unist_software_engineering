package factory;

import static org.junit.Assert.assertTrue;
import java.io.IOException;

// cover branch of milestone2
public class ThreeArgsFactoryTest {

    @Test
    public void testNegativeAgeCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "-1", "gradstudent");

        // invalid age input (non-positive)
        String[] input = {"F", "-1", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Non-positive age) Please try again with appropriate age."));

        System.out.println("passed ThreeArgsFactory_NegativeAgeCheckValidity");
    }

    @Test
    public void testNonIntegerAgeCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "s", "gradstudent");

        // invalid age input (not an integer)
        String[] input = {"F", "s", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Not an integer) Please try again with appropriate age."));

        System.out.println("passed ThreeArgsFactory_NonIntegerAgeCheckValidity");
    }

    @Test
    public void testGenderCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("D", "32", "gradstudent");

        // invalid gender input
        String[] input = {"D", "32", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong gender input. Please try again with F for female or M for male."));

        System.out.println("passed ThreeArgsFactory_GenderCheckValidity");
    }

    @Test
    public void testOccupationCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("F", "32", "gradstudents");

        // invalid occupation input
        String[] input = {"F", "32", "gradstudents"};
        assertTrue(actualInput.checkValidity(input).equals("Can't search inappropriate occupation, gradstudents. Please try again with appropriate occupation.%n"));

        System.out.println("passed ThreeArgsFactory_OccupationCheckValidity");
    }
}
