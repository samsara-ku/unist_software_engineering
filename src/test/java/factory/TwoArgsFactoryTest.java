package factory;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

// cover branch of milestone1
public class TwoArgsFactoryTest {

    @Test
    public void testCheckValidity(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comedy", "unemployed");

        // do nothing in this class
        String[] input = {};
        assertTrue(actualInput.checkValidity(input).equals(""));

        System.out.println("passed TwoArgsFactory_checkValidity");
    }

    @Test
    public void testPrintResult(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comed", "unemployed");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult");
    }

}
