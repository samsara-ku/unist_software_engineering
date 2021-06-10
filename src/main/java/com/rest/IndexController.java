package com.rest;

import com.csvtomongo.model.Movies;
import com.csvtomongo.repository.MovieRepository;
import factory.rating.RatingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController extends ControllerUtil {

  @Autowired
  private MovieRepository movieRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Object index() {
    RatingUtils ratingUtils = new RatingUtils();
    ratingUtils.getMovieRankingList(10);

    return getMovieList(ratingUtils.getMovieRankingList(10), "indexMovie");
  }

  @RequestMapping(value = "/topmovies", method = RequestMethod.GET)
  public Object top() {
    RatingUtils ratingUtils = new RatingUtils();
    ratingUtils.getMovieRankingList(10);

    return getMovieList(ratingUtils.getMovieRankingList(10), "indexMovie");
  }

  @RequestMapping(value = "/movies", method = RequestMethod.GET)
  public Object allMovies() {
    return movieRepository.findAll();
  }
}
