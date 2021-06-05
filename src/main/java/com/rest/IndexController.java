package com.rest;

import factory.rating.RatingUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController extends ControllerUtil {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Object index() {
    RatingUtils ratingUtils = new RatingUtils();
    ratingUtils.getMovieRankingList(10);

    return getMovieList(ratingUtils.getMovieRankingList(10), "indexMovie");
  }
}
