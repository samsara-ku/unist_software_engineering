package factory.rating;

import factory.user.LoadTwoArgs;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.HashMap.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class RatingWithTwoArgsTest {

    @Test
    public void RatingWithTwoArgsTest() {
        LoadTwoArgs actualLoad = new LoadTwoArgs("action", "artist");
        RatingWithTwoArgs actualRating = new RatingWithTwoArgs(actualLoad.getMovieList(),
            actualLoad.getUserList());
        ArrayList<String> testMovieIdList = actualRating.getMovieIdList();
        ArrayList<String> testUserIdList = actualRating.getUserIdList();
        actualRating.setIdLists();

        assertEquals(testMovieIdList, actualRating.getMovieIdList());
        assertEquals(testUserIdList, actualRating.getUserIdList());
        System.out.println("passed RatingWithTwoArgs_setIdLIsts");
    }

    @Test
    public void TestGetAverageRating() {
        LoadTwoArgs actualLoad = new LoadTwoArgs("action", "artist");
        RatingWithTwoArgs actualRating = new RatingWithTwoArgs(actualLoad.getMovieList(),
            actualLoad.getUserList());
        assertTrue(0 != actualRating.getAverageRating());
        System.out.println("passed RatingWithTwoArgs_getAverageRating");
    }

    @Test
    public void TestGetMovieIdList() {
        LoadTwoArgs actualLoad = new LoadTwoArgs("action", "artist");
        RatingWithTwoArgs actualRating = new RatingWithTwoArgs(actualLoad.getMovieList(),
            actualLoad.getUserList());
        ArrayList<String> testList = actualRating.getMovieIdList();
        assertEquals(testList, actualRating.getMovieIdList());
        System.out.println("passed RatingWithTwoArgs_getMovieIdList");
    }
    
    @Test
    public void TestGetUserIdList() {
        LoadTwoArgs actualLoad = new LoadTwoArgs("action", "artist");
        RatingWithTwoArgs actualRating = new RatingWithTwoArgs(actualLoad.getMovieList(),
            actualLoad.getUserList());
            ArrayList<String> testList = actualRating.getUserIdList();
            assertEquals(testList, actualRating.getUserIdList());
        System.out.println("passed RatingWithTwoArgs_getUserIdList");
    }
}