package factory;

import static org.junit.Assert.assertTrue;
import java.io.IOException;

import org.junit.Test;
// cover branch of milestone2
public class ThreeArgsFactoryTest {

    @Test
    public void testNegativeAgeCheckValidity(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("", "", "");

        // invalid age input (non-positive)
        String[] input = {"F", "-1", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Non-positive age) Please try again with appropriate age."));

        System.out.println("passed ThreeArgsFactory_NegativeAgeCheckValidity");
    }

    @Test
    public void testNonIntegerAgeCheckValidity(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("", "", "");

        // invalid age input (not an integer)
        String[] input = {"F", "s", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Not an integer) Please try again with appropriate age."));

        System.out.println("passed ThreeArgsFactory_NonIntegerAgeCheckValidity");
    }

    @Test
    public void testGenderCheckValidity(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("", "", "");

        // invalid gender input
        String[] input = {"D", "32", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong gender input. Please try again with F for female or M for male."));

        System.out.println("passed ThreeArgsFactory_GenderCheckValidity");
    }

    @Test
    public void testOccupationCheckValidity(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("", "", "");

        // invalid occupation input
        String[] input = {"F", "32", "gradstudents"};
        String testOccupation = "gradstudents";
        assertTrue(actualInput.checkValidity(input).equals(String.format("Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n", testOccupation)));

        System.out.println("passed ThreeArgsFactory_OccupationCheckValidity");
    }

    @Test
    public void testCheckValidity1(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("", "", "");

        // valid input
        String[] input = {"F", "32", "gradstudent"};
        assertTrue(actualInput.checkValidity(input).equals(""));

        System.out.println("passed ThreeArgsFactory_CheckValidity1");
    }

    @Test
    public void testCheckValidity2(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("", "", "");

        // valid input
        String[] input = {"", "", ""};
        assertTrue(actualInput.checkValidity(input).equals(""));

        System.out.println("passed ThreeArgsFactory_CheckValidity2");
    }

    @Test
    public void testPrintResult(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("F", "32", "gradstudent");

        try {
            actualInput.printResult();
            System.out.println("passed ThreeArgsFactory_printResult");
        } catch (Exception e) {
            System.out.println("printResult doesn't work");
        }
    }
}
