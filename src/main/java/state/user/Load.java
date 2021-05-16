package state.user;

import java.util.ArrayList;

public class Load {

  public boolean hasContained(ArrayList<String> truthArr, ArrayList<String> target) {
    return truthArr.stream().filter(i -> target.stream().anyMatch(e -> e.equalsIgnoreCase(i)))
        .count() == target.size();
  }

  public boolean hasContained(ArrayList<String> truthArr, String target) {
    return truthArr.stream().anyMatch(i -> i.equalsIgnoreCase(target));
  }
}
