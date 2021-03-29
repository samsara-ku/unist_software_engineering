package handlelist;

import data.Load;
import handlelist.HandleList;
import org.junit.Test;


public class HandleListTest {

    @Test
    public void testLoad() {
        Load test = new Load("comedy", "unemployed");
        HandleList listHandler = new HandleList(test.getMovieList(), test.getUserList());

        System.out.println(listHandler.getAverageRating());

    }
}
