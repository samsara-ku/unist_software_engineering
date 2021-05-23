package factory.user;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class LoadMoreThanTwoArgsTest {

    @Test
    public void LoadMoreThanTwoArgsTest() {
        LoadMoreThanTwoArgs actualInput = new LoadMoreThanTwoArgs("F", "25", "artist");
        
        assertNotNull(actualInput.getAge());
        System.out.println("passed LoadMoreThanTwoArgs_setAge");
        
        assertNotNull(actualInput.getGender());
        System.out.println("passed LoadMoreThanTwoArgs_setGender");
        
        assertNotNull(actualInput.getOccupation());
        System.out.println("passed LoadMoreThanTwoArgs_setOccupation");
        
        assertNotNull(actualInput.getUserList());
        System.out.println("passed LoadMoreThanTwoArgs_setUserList");
    }

    @Test
    public void TestGet() {
        LoadMoreThanTwoArgs actualInput = new LoadMoreThanTwoArgs("F", "25", "artist");
        LoadMoreThanTwoArgs test_no_sex = new LoadMoreThanTwoArgs("", "25", "artist");
        LoadMoreThanTwoArgs test_no_age = new LoadMoreThanTwoArgs("F", "", "artist");
        LoadMoreThanTwoArgs test_no_occu = new LoadMoreThanTwoArgs("F", "25", "");

        assertEquals(actualInput.getAge(), "25");
        System.out.println("passed LoadMoreThanTwoArgs_getAge");

        assertEquals(actualInput.getGender(), "F");
        System.out.println("passed LoadMoreThanTwoArgs_getGender");
        
        assertEquals(actualInput.getOccupation(), "artist");
        System.out.println("passed LoadMoreThanTwoArgs_getOccupation");

        assertTrue(actualInput.getUserList().size() <= test_no_sex.getUserList().size());
        assertTrue(actualInput.getUserList().size() <= test_no_age.getUserList().size());
        assertTrue(actualInput.getUserList().size() <= test_no_occu.getUserList().size());
        System.out.println("passed LoadMoreThanTwoArgs_getUserList");
    }

    @Test
    public void TestFindUserWithProperAge() {
        LoadMoreThanTwoArgs actualInput = new LoadMoreThanTwoArgs("F", "", "artist");
        LoadMoreThanTwoArgs test_age_1 = new LoadMoreThanTwoArgs("F", "5", "artist");
        LoadMoreThanTwoArgs test_age_18 = new LoadMoreThanTwoArgs("F", "20", "artist");
        LoadMoreThanTwoArgs test_age_25 = new LoadMoreThanTwoArgs("F", "30", "artist");
        LoadMoreThanTwoArgs test_age_35 = new LoadMoreThanTwoArgs("F", "40", "artist");
        LoadMoreThanTwoArgs test_age_45 = new LoadMoreThanTwoArgs("F", "48", "artist");
        LoadMoreThanTwoArgs test_age_50 = new LoadMoreThanTwoArgs("F", "55", "artist");
        LoadMoreThanTwoArgs test_age_56 = new LoadMoreThanTwoArgs("F", "57", "artist");

        assertNotNull(test_age_1.getUserList());
        assertNotNull(test_age_18.getUserList());
        assertNotNull(test_age_25.getUserList());
        assertNotNull(test_age_35.getUserList());
        assertNotNull(test_age_45.getUserList());
        assertNotNull(test_age_50.getUserList());
        assertNotNull(test_age_56.getUserList());
        System.out.println("passed LoadMoreThanTwoArgs_findUserWithProperAge1");

        assertTrue(actualInput.getUserList().size() >= test_age_1.getUserList().size());
        assertTrue(actualInput.getUserList().size() >= test_age_18.getUserList().size());
        assertTrue(actualInput.getUserList().size() >= test_age_25.getUserList().size());
        assertTrue(actualInput.getUserList().size() >= test_age_35.getUserList().size());
        assertTrue(actualInput.getUserList().size() >= test_age_45.getUserList().size());
        assertTrue(actualInput.getUserList().size() >= test_age_50.getUserList().size());
        assertTrue(actualInput.getUserList().size() >= test_age_56.getUserList().size());
        System.out.println("passed LoadMoreThanTwoArgs_findUserWithProperAge2");
    }
}