package factory;

import factory.link.LinkUserAndRating;
import factory.rating.MovieTitleRecommend;
import factory.user.LoadMovieTitle;
import java.util.ArrayList;

public class MovieRecommendFactory {

  private final String title;
  private final String limit;
  private String errorMessage;

  public MovieRecommendFactory(String title, String limit) {
    this.title = title;
    this.limit = limit;
    this.errorMessage = "";
  }

  public ArrayList<String> getResult() {
    LoadMovieTitle loadMovieTitle = new LoadMovieTitle(this.title);

    MovieTitleRecommend movieTitleRecommend = new MovieTitleRecommend(loadMovieTitle.getUserList(),
        Integer.parseInt(this.limit), loadMovieTitle.getMovieId(), loadMovieTitle.getMovieGenre());

    LinkUserAndRating result = new LinkUserAndRating(movieTitleRecommend.getTopRecommendMovies(),
        Integer.parseInt(this.limit));

    return result.getLinkList();
  }

  public boolean titleIsValid() {
    LoadMovieTitle loadMovieTitle = new LoadMovieTitle(this.title);
    if (this.title.isEmpty()) {
      this.errorMessage += "There is no title input. Please input title.\n";
      return false;
    } else if (loadMovieTitle.getMovieId().equals("0")) {
      this.errorMessage += "Cannot find movies with your title input. Please try again with accurate movie title.\n";
      return false;
    }
    return true;
  }

  public boolean limitIsValid() {
    if (!this.limit.isEmpty()) {
      try {
        int limitParse = Integer.parseInt(limit);
        if (limitParse <= 0 || limitParse > 100) {
          this.errorMessage += "Wrong limit input. (Not in range) Please try again with appropriate limit that 1 to 100.\n";
          return false;
        }
      } catch (Exception e) {
        this.errorMessage += "Wrong limit input. (Not an integer) Please try again with appropriate limit.\n";
        return false;
      }
    }
    return true;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }
}
