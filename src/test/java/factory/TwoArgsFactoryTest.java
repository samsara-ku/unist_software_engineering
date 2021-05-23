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
    public void testPrintResult1(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comedy", "unemployed");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult1");
    }

    @Test
    public void testPrintResult2(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comed", "unemployed");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult2");
    }

    @Test
    public void testPrintResult3(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comedy", "unemployed");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult3");
    }

    @Test
    public void testPrintResult4(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comedy", "unemploye");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult4");
    }

    @Test
    public void testPrintResult5(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comed", "unemploye");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult5");
    }

    @Test
    public void testPrintResult6(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comedy|romance|drama|animation|action|thriller|sci-fi|musical|mystery", "unemployed");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult6");
    }

    @Test
    public void testPrintResult7(){
        TwoArgsFactory actualInput = new TwoArgsFactory("comedy", "unemployed");
        actualInput.printResult();
        System.out.println("passed TwoArgsFactory_printResult7");
    }

    

}
