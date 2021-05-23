package factory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEqaul;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;

import org.junit.Test;
// cover branch of milestone2
public class FourArgsFactoryTest {

    @Test
    public void testNegativeAgeCheckValidity(){
        FourArgsFactory actualInput = new FourArgsFactory("", "", "", "");

        // invalid age input (non-positive)
        String[] input = {"F", "-1", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Non-positive age) Please try again with appropriate age."));

        System.out.println("passed FourArgsFactory_NegativeAgeCheckValidity");
    }

    @Test
    public void testNonIntegerAgeCheckValidity(){
        FourArgsFactory actualInput = new FourArgsFactory("", "", "", "");

        // invalid age input (not an integer)
        String[] input = {"F", "s", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong age input. (Not an integer) Please try again with appropriate age."));

        System.out.println("passed FourArgsFactory_NonIntegerAgeCheckValidity");
    }

    @Test
    public void testGenderCheckValidity(){
        FourArgsFactory actualInput = new FourArgsFactory("", "", "", "");

        // invalid gender input
        String[] input = {"D", "32", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals("Wrong gender input. Please try again with F for female or M for male."));

        System.out.println("passed FourArgsFactory_GenderCheckValidity");
    }

    @Test
    public void testOccupationCheckValidity(){
        FourArgsFactory actualInput = new FourArgsFactory("", "", "", "");

        // invalid occupation input
        String[] input = {"F", "32", "gradstudents", "comedy"};
        String testOccupation = "gradstudents";
        assertTrue(actualInput.checkValidity(input).equals(String.format("Can't search inappropriate occupation, \"%s\". Please try again with appropriate occupation.%n", testOccupation)));

        System.out.println("passed FourArgsFactory_OccupationCheckValidity");
    }

    @Test
    public void testGenreCheckValidity(){
        FourArgsFactory actualInput = new FourArgsFactory("", "", "", "");

        // invalid occupation input
        String[] input = {"F", "32", "gradstudent", "comed"};
        int cnt = 0;



        assertTrue(actualInput.checkValidity(input).equals("Can't search because there is inappropriate category. Please try again with appropriate category."));
        assertEqual(cnt, )

        System.out.println("passed FourArgsFactory_OccupationCheckValidity");
    }

    @Test
    public void testCheckValidity(){
        FourArgsFactory actualInput = new FourArgsFactory("", "", "", "");

        // valid input
        String[] input = {"F", "32", "gradstudent", "comedy"};
        assertTrue(actualInput.checkValidity(input).equals(""));

        System.out.println("passed FourArgsFactory_CheckValidity");
    }

    @Test
    public void testGetResult(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("F", "32", "gradstudent", "comedy");

        assertNotNull(actualInput.getResult());

        System.out.println("passed FourArgsFactory_GetResult");
    }

    @Test
    public void testPrintResult(){
        ThreeArgsFactory actualInput = new ThreeArgsFactory("F", "32", "gradstudent", "comedy");

        try {
            actualInput.printResult;
            System.out.println("passed FourArgsFactory_printResult");
        } catch (Exception e) {
            System.out.println("printResult doesn't work");
        }
    }
}
