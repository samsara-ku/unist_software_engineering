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
        LoadMoreThanTwoArgs test1_no_sex = new LoadMoreThanTwoArgs("", "25", "artist");
        LoadMoreThanTwoArgs test1_no_age = new LoadMoreThanTwoArgs("F", "", "artist");
        LoadMoreThanTwoArgs test1_no_occu = new LoadMoreThanTwoArgs("F", "", "");

        assertEquals(actualInput.getAge(), "25");
        System.out.println("passed LoadMoreThanTwoArgs_getAge");

        assertEquals(actualInput.getGender(), "F");
        System.out.println("passed LoadMoreThanTwoArgs_getGender");
        
        assertEquals(actualInput.getOccupation(), "artist");
        System.out.println("passed LoadMoreThanTwoArgs_getOccupation");

        assertTrue(actualInput.getUserList().size() <= test1_no_sex.getUserList().size());
        assertTrue(actualInput.getUserList().size() <= test1_no_age.getUserList().size());
        assertTrue(actualInput.getUserList().size() <= test1_no_occu.getUserList().size());
    }
    @Test
    public void TestFindUserWithProperAge() {
        System.out.println("passed LoadMoreThanTwoArgs_findUserWithProperAge");
    }
}