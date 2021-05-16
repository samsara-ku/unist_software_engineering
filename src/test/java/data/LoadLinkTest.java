package data;

import static org.junit.Assert.assertTrue;

import handlelist.SimilarUserRecommend;
import org.junit.Test;

public class LoadLinkTest {

  @Test
  public void testSetLinkList() {
    LoadUser test_no_input = new LoadUser("", "", "");
    SimilarUserRecommend best = new SimilarUserRecommend(test_no_input.getUserList());

    LoadLink result = new LoadLink(best.getTop10());
    assertTrue(result.getLinkList().size() != 0);
  }
}
