package com.rest;


import factory.MovieRecommendFactory;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendController extends ControllerUtil {

  @Autowired
  private MongoTemplate mongoTemplate;

  @RequestMapping(value = "/movies/recommendations", method = RequestMethod.GET)
  public Object recommend(@RequestParam("title") String title, @RequestParam("limit") String limit,
      MovieRecommend mediator) {
    MovieRecommendFactory movieRecommendFactory = new MovieRecommendFactory(mediator.getTitle(),
        mediator.getLimit());
    boolean titleIsValid = movieRecommendFactory.titleIsValid();
    boolean limitIsValid = movieRecommendFactory.limitIsValid();

    if (!titleIsValid || !limitIsValid) {
      String error = movieRecommendFactory.getErrorMessage();

      HashMap<String, String> temp = new HashMap<>();
      temp.put("error", error);

      return temp;
    }

    return getMovieList(movieRecommendFactory.getResult(), "MovieRecommend");
  }
}