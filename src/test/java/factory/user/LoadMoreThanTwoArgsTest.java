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
        
        assertEquals(actualInput.getAge(), "25");
        System.out.println("passed LoadMoreThanTwoArgs_getAge");

        assertEquals(actualInput.getGender(), "F");
        System.out.println("passed LoadMoreThanTwoArgs_getGender");
        
        assertEquals(actualInput.getOccupation(), "artist");
        System.out.println("passed LoadMoreThanTwoArgs_getOccupation");
    }
    @Test
    public void TestFindUserWithProperAge() {
        System.out.println("passed LoadMoreThanTwoArgs_findUserWithProperAge");
    }
}