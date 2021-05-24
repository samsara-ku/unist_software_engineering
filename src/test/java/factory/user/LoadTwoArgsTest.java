package factory.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"./pom.xml"})
//@SpringBootTest
public class LoadTwoArgsTest {

    private ArrayList<String> testArrayList = new ArrayList<String>(Arrays.asList("action", "drama"));

    @Test
    public void LoadTwoArgsTest() {
        LoadTwoArgs actualInput = new LoadTwoArgs("action|drama", "artist");
        
        assertNotNull(actualInput.getCategories());
        System.out.println("passed LoadTwoArgs_setCategories");
        
        assertNotNull(actualInput.getOccupation());
        System.out.println("passed LoadTwoArgs_setOccupation");
        
        assertNotNull(actualInput.getUserList());
        System.out.println("passed LoadTwoArgs_setUserList");
        
        assertNotNull(actualInput.getMovieList());
        System.out.println("passed LoadTwoArgs_setMovieList");
    }

    @Test
    public void TestGet() {
        LoadTwoArgs actualInput = new LoadTwoArgs("action|drama", "artist");
        LoadTwoArgs test1_no_occu = new LoadTwoArgs("action|drama", "");
        LoadTwoArgs test2_no_cate = new LoadTwoArgs("", "artist");
        ArrayList<String> emptyList = new ArrayList<String>(Arrays.asList(""));
        assertEquals(testArrayList, actualInput.getCategories());
        System.out.println("passed LoadTwoArgs_setCategories");
        
        assertEquals("artist", actualInput.getOccupation());
        System.out.println("passed LoadTwoArgs_setOccupation");
        
        assertTrue(test1_no_occu.getUserList().size() >= actualInput.getUserList().size());
        System.out.println("passed LoadTwoArgs_setUserList");
        
        assertTrue(test2_no_cate.getCategories().equals(emptyList));
        System.out.println("passed LoadTwoArgs_setMovieList");
    }

    @Test
    public void TestParseCategory() {
        
        LoadTwoArgs actualInput = new LoadTwoArgs("action|drama", "artist");
        assertEquals(actualInput.parseCategory("action|drama"), testArrayList);
        assertNotNull(actualInput.parseCategory("action|drama"));
        System.out.println("passed LoadTwoArgs_parseCategory");
    }
}