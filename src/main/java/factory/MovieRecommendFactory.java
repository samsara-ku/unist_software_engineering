package factory;

import factory.link.LinkUserAndRating;
import factory.rating.MovieTitleRecommend;
import factory.user.LoadMovieTitle;
import java.util.ArrayList;

public class MovieRecommendFactory {
  private final String title;
  private final String limit;

  public MovieRecommendFactory(String title, String limit){
    this.title = title;
    this.limit = limit;
  }

  public ArrayList<String> getResult(){
    LoadMovieTitle loadMovieTitle = new LoadMovieTitle(this.title);

    MovieTitleRecommend movieTitleRecommend = new MovieTitleRecommend(loadMovieTitle.getUserList(),
        Integer.parseInt(this.limit), loadMovieTitle.getMovieId(), loadMovieTitle.getMovieGenre());

    LinkUserAndRating result = new LinkUserAndRating(movieTitleRecommend.getTopRecommendMovies(),
        Integer.parseInt(this.limit));


    return result.getLinkList();
  }

}
