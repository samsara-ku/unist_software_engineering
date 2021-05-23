package factory.user;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;

import org.junit.Test;

public class LoadMovieTitleTest {

    @Test
    public void LoadMovieTitleTest() {
        LoadMovieTitle actualInput = new LoadMovieTitle("Toy Story (1995)");
        LoadMovieTitle wrongInput = new LoadMovieTitle("Toy Stor (1995)");
        ArrayList<String> emptyString = new ArrayList<>();
        String testMovieGenre = actualInput.getMovieGenre();
        String testMovieId = actualInput.getMovieId();

        assertNotEquals("", actualInput.getUserList());
        assertEquals(emptyString, wrongInput.getUserList());
        System.out.println("passed LoadMovieTitle_setUserWatchedMovie");

        assertNotEquals("", testMovieId);
        assertNotEquals("", testMovieGenre);
        assertEquals(testMovieId, actualInput.getMovieId());
        assertEquals(testMovieGenre, actualInput.getMovieGenre());
        System.out.println("passed LoadMovieTitle_setMovieIdAndGenre");
    }

    @Test
    public void TestParseMovieTitle() {
        LoadMovieTitle actualInput = new LoadMovieTitle("Toy Story (1995)");
        assertEquals("toystory(1995)", actualInput.parseMovieTitle("Toy Story (1995)"));
        System.out.println("passed LoadMovieTitle_parseMovieTitle");
    }
}