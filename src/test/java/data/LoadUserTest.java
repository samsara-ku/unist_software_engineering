package data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoadUserTest {

  @Test
  public void testHasContained() {
    String[] truthArr = {"I", "AM", "TEST"};
    LoadUser data = new LoadUser("", "", "");

    assertTrue(data.hasContained(truthArr, "I"));
    assertFalse(data.hasContained(truthArr, "Th"));

    String[] testArr1 = {"I", "AM", "TEST"};

    assertTrue(data.hasContained(truthArr, testArr1));

    String[] testArr2 = {"STT", "SE"};

    assertFalse(data.hasContained(truthArr, testArr2));
  }

  @Test
  public void testSetUserList() {
    LoadUser test1_no_input = new LoadUser("", "", "");
    LoadUser test1_no_occu = new LoadUser("F", "60", "");

    assertTrue(test1_no_input.getUserList().size() >= test1_no_occu.getUserList().size());

    LoadUser test2_no_occu = new LoadUser("M", "17", "");
    LoadUser test2_with_occu = new LoadUser("M", "17", "artist");

    assertTrue(test2_no_occu.getUserList().size() >= test2_with_occu.getUserList().size());

    LoadUser test3_no_occu = new LoadUser("M", "46", "");

    assertTrue(
        test3_no_occu.getUserList().containsValue(0.75) || test3_no_occu.getUserList()
            .containsValue(0.5));

    LoadUser test4_full_input = new LoadUser("M", "55", "");

    assertTrue(
        test4_full_input.getUserList().containsValue(0.75) || test4_full_input.getUserList()
            .containsValue(0.5));
  }
}
