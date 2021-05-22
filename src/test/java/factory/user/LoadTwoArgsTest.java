package factory.user;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class LoadTwoArgsTest {

    private LoadTwoArgs testLoadTwoArgs = new LoadTwoArgs("action|drama", "artist");
    private ArrayList<String> testArrayList = new ArrayList<String>(Arrays.asList("action", "drama"));

    @Test
    public void TestParseCategory() {
        assertEquals(testLoadTwoArgs.parseCategory("action|drama"), testArrayList);
        assertNotNull(testLoadTwoArgs.parseCategory("action|drama"));
        System.out.println("passed LoadTwoArgs_parseCategory");
    }

    @Test
    public void TestGetCategories() {
        assertEquals(testLoadTwoArgs.getCategories(), testArrayList);
        System.out.println("passed LoadTwoArgs_getCategories");
    }

    @Test
    public void TestSetCategories() {
        assertNotNull(testLoadTwoArgs.getCategories());
        System.out.println("passed LoadTwoArgs_setCategories");
    }

    @Test
    public void TestGetOccupation() {
        assertEquals(testLoadTwoArgs.getOccupation(), "artist");
        System.out.println("passed LoadTwoArgs_getOccupation");
    }

    @Test
    public void TestSetOccupation() {
        assertNotNull(testLoadTwoArgs.getOccupation());
        System.out.println("passed LoadTwoArgs_setOccupation");
    }

    @Test
    public void TestGetUserList() {
        
        System.out.println("passed LoadTwoArgs_getUserList");
    }

    @Test
    public void TestSetUserList() {
        System.out.println("passed LoadTwoArgs_setUserList");
    }

    @Test
    public void TestGetMovieList() {
        System.out.println("passed LoadTwoArgs_getMovieList");
    }

    @Test
    public void TestSetMovieList() {
        System.out.println("passed LoadTwoArgs_setMovieList");
    }
}