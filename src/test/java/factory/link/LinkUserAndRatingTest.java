package factory.link;

import factory.user.LoadMoreThanTwoArgs;
import factory.rating.RatingMoreThanTwoArgs;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import factory.link.LinkUserAndRating;

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