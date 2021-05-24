package factory.rating;

import factory.user.LoadMoreThanTwoArgs;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class RatingMoreThanTwoArgsTest {

    @Test
    public void TestnumberOfMovie() {
        LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
        RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList());
        actualRating.getTop10();
        assertNotNull(actualRating.numberOfMovie());
        System.out.println("passed RatingMoreThanTwoArgs_numberOfMovie");
    }
    
    @Test
    public void TestSumOfRating() {
        LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
        RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList());
        actualRating.getTop10();
        assertNotNull(actualRating.sumOfRating());
        System.out.println("passed RatingMoreThanTwoArgs_sumOfRating");
    }

    @Test
    public void TestSetTop10() {
        LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
        RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList());
        actualRating.getTop10();
        System.out.println(actualRating.getTop10());
        System.out.println("passed RatingMoreThanTwoArgs_setTop10");
    }

    @Test
    public void TestGet() {
        LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
        RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList());
        actualRating.getTop10();
        
        assertNotNull(actualRating.getTop10());
        System.out.println("passed RatingMoreThanTwoArgs_getTop10");

        assertNotNull(actualRating.getTop10_num());
        System.out.println("passed RatingMoreThanTwoArgs_getTop10_num");

        assertNotNull(actualRating.getTop10_rat());
        System.out.println("passed RatingMoreThanTwoArgs_getTop10_rat");
        
        assertNotNull(actualRating.getUserIdIndexList());
        System.out.println("passed RatingMoreThanTwoArgs_getUserIdIndexList");
    }
}