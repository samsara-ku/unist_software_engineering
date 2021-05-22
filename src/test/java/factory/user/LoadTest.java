package factory.user;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class LoadTest {

    
    private LoadTwoArgs TestLoadTwoArgs = new LoadTwoArgs("action|drama", "");
    
    @Test
    public void TestHasContained() {
        assertTrue(TestLoadTwoArgs.hasContained(TestLoadTwoArgs.getCategories(), "action"));
        assertTrue(TestLoadTwoArgs.hasContained(TestLoadTwoArgs.getCategories(), "drama"));
        assertFalse(TestLoadTwoArgs.hasContained(TestLoadTwoArgs.getCategories(), "comedy"));
        
        ArrayList<String> testTarget = new ArrayList<String>();
        testTarget.add("action");
        assertTrue(TestLoadTwoArgs.hasContained(TestLoadTwoArgs.getCategories(), testTarget));
        testTarget.add("drama");
        assertTrue(TestLoadTwoArgs.hasContained(TestLoadTwoArgs.getCategories(), testTarget));
        testTarget.add("comedy");
        assertFalse(TestLoadTwoArgs.hasContained(TestLoadTwoArgs.getCategories(), testTarget));
        System.out.println("passed Load_hasContained");
    }

    @Test
    public void TestFindOccupationNumber() {
        assertEquals(TestLoadTwoArgs.findOccupationNumber("artist"), "2");
        assertNull(TestLoadTwoArgs.findOccupationNumber("fake_occu"));
        System.out.print("passed Load_findOccupationNumber");
    }
}