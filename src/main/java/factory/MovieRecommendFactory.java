package factory;

import factory.link.LinkUserAndRating;
import factory.rating.MovieTitleRecommend;
import factory.user.LoadMovieTitle;
import java.util.ArrayList;

public class MovieRecommendFactory {

  private final String title;
  private final int limit;
  private String errorMessage;

  public MovieRecommendFactory(String title, int limit) {
    this.title = title;
    this.limit = limit;
    this.errorMessage = "";
  }

  public ArrayList<String> getResult() {
    LoadMovieTitle loadMovieTitle = new LoadMovieTitle(this.title);

    MovieTitleRecommend movieTitleRecommend = new MovieTitleRecommend(loadMovieTitle.getUserList(),
        this.limit, loadMovieTitle.getMovieId(), loadMovieTitle.getMovieGenre());

    LinkUserAndRating result = new LinkUserAndRating(movieTitleRecommend.getTopRecommendMovies(),
        this.limit);

    return result.getLinkList();
  }

  public boolean titleIsValid() {
    LoadMovieTitle loadMovieTitle = new LoadMovieTitle(this.title);
    if (this.title.isEmpty()) {
      this.errorMessage += "Please input title.\n";
      return false;
    } else if (loadMovieTitle.getMovieId().equals("0")) {
      this.errorMessage += "Wrong title input. Please try again with accurate movie title.\n";
      return false;
    }
    return true;
  }

  public boolean limitIsValid() {
    if (this.limit <= 0 || this.limit > 100) {
      this.errorMessage += "Wrong limit input. (Not in range) Please try again with appropriate limit that 1 to 100.\n";
      return false;
    }
    return true;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }
}
