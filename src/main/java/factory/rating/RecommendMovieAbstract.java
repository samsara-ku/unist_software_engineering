package factory.rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class RecommendMovieAbstract {

  public abstract HashMap<Integer, Double> sumOfRating();

  public abstract HashMap<Integer, Integer> numberOfMovie();

  public List<Entry<Integer, Double>> sortWithRating(HashMap<Integer, Double> avgRating) {
    // Sorting avgRating (descending order)
    List<Entry<Integer, Double>> sortedRating = new ArrayList<>(
        avgRating.entrySet());

    sortedRating.sort((obj1, obj2) -> obj2.getValue().compareTo(obj1.getValue()));

    return sortedRating;
  }

  public ArrayList<Integer> sortGetTopN(HashMap<Integer, Integer> numberOfMovie, int n) {
    // Sorting number_list (descending order)
    List<Entry<Integer, Integer>> number_list = new ArrayList<>(
        numberOfMovie.entrySet());

    number_list.sort((obj1, obj2) -> obj2.getValue().compareTo(obj1.getValue()));

    // Top 30 most viewed movie
    ArrayList<Integer> topN = new ArrayList<>();
    int ck = 1;

    for (Entry<Integer, Integer> entry : number_list) {
      if (ck <= n) {
        topN.add(entry.getKey());
        ck = ck + 1;
      }
    }
    return topN;
  }
}
