package factory.rating;

import factory.user.LoadMovieTitle;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MovieTitleRecommendTest {
    
    @Test
    public void MovieTitleRecommendTest() {
        LoadMovieTitle loadMovieTitle = new LoadMovieTitle("Toy Story (1995)");
        MovieTitleRecommend actualInput = new MovieTitleRecommend(loadMovieTitle.getUserList(),
            10, loadMovieTitle.getMovieId(), loadMovieTitle.getMovieGenre());
        actualInput.recommendMovies();

        assertNotNull(actualInput.numberOfMovie());
        System.out.println("passed MovieTitleRecommend_numberOfMovie");
        assertNotNull(actualInput.sumOfRating());
        System.out.println("passed MovieTitleRecommend_sumOfRating");
        assertNotNull(actualInput.getTopRecommendMovies());
        System.out.println("passed MovieTitleRecommend_getTopRecommendMovies");
        System.out.println("passed MovieTitleRecommend_recommendMovies");
    }
    
    @Test
    public void TestGenreSimilarity() {
        LoadMovieTitle loadMovieTitle = new LoadMovieTitle("Toy Story (1995)");
        MovieTitleRecommend actualInput = new MovieTitleRecommend(loadMovieTitle.getUserList(),
            10, loadMovieTitle.getMovieId(), loadMovieTitle.getMovieGenre());

        String[] testArr1 = {"action", "drama"};
        String[] testArr2 = {"drama", "action"};
        String[] testArr3 = {"drama"};
        assertTrue(actualInput.genreSimilarity(testArr1, testArr2) >= actualInput.genreSimilarity(testArr1, testArr3));
        System.out.println("passed MovieTitleRecommend_genreSimilarity");
    }
}