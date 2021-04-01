package handlelist;
import data.Load;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandleListTest {

		@Test
		public void testLoad() {
			Load actualInput = new Load("comedy|action", "unemployed");

			assertNotNull(actualInput.getCategories());
			System.println("passed test_setCategories");

			assertNotNull(actualInput.getOccupation());
			System.println("passed test_setOccupation");

			assertNotNull(actualInput.getMovieList());
			System.println("passed test_setMovieList");

			assertNotNull(actualInput.getUserList());
			System.println("passed test_setUserList");

			System.println("passed test_Load");
		}

		@Test
		public void testparseCategory() {
			String[] expectedResult = ["comedy", "action"];
			String[] actualResult = new Load.parseCategory("comedy|action");
			assertArrayEquals(expectedResult, actualResult);
			System.println("passed test_parseCategory");
		}

		@Test
		public void testCategories() {
			Load actualInput = new Load("comedy|action", "unemployed");
			assertArrayEquals(actualInput.getCategories(), ["comedy", "action"]);
			System.println("passed test_getCategories");

			int actualResult = 2;
			assertEquals(actualInput.categoriesLength(), 2);
			System.println("passed test_CategoriesLength");		
		}

		@Test
		public void testOccupation() {
			Load actualInput = new Load("comedy|action", "unemployed");
			assertEquals("unemployed", actualInput.getOccupation);
			System.println("passed test_getOccupation");
		}

    @Test
    public void testLoad() {
			Load expectedResults = new Load("comedy", "unemployed");
		}
}
