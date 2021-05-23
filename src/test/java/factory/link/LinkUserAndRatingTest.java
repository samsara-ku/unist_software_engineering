package factory.link;

import factory.user.LoadMoreThanTwoArgs;
import factory.rating.RatingMoreThanTwoArgs;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

//import org.springframework.boot.test.context.SpringBootTest;

import factory.link.LinkUserAndRating;

//@SpringBootTest
public class LinkUserAndRatingTest {

    @Test
    public void LinkUserAndRatingTest() {
        LoadMoreThanTwoArgs actualLoad = new LoadMoreThanTwoArgs("F", "25", "artist");
        RatingMoreThanTwoArgs actualRating = new RatingMoreThanTwoArgs(actualLoad.getUserList(), "action");
        LinkUserAndRating actualInput = new LinkUserAndRating(actualRating.getTop10(), 10);
        assertNotNull(actualInput.getLinkList());
        System.out.println("passed LinkUserAndRating_setLinkList");
    }
}   