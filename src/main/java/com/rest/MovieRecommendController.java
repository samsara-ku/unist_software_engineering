package com.rest;


import factory.MovieRecommendFactory;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendController {

  @ResponseBody
  @GetMapping("/movies/recommendations")
  public Object recommend(@RequestBody(required = false) MovieRecommend mediator) {
    MovieRecommendFactory movieRecommendFactory = new MovieRecommendFactory(mediator.getTitle(),
        mediator.getLimit());
    boolean titleIsValid = movieRecommendFactory.titleIsValid();
    boolean limitIsValid = movieRecommendFactory.limitIsValid();

    if (!titleIsValid || !limitIsValid) {
      return movieRecommendFactory.getErrorMessage();
    }

    ArrayList<HashMap<String, String>> object = new ArrayList<>();

    movieRecommendFactory.getResult().forEach(i -> {
      HashMap<String, String> temp = new HashMap<>();
      temp.put("imdb", String.format("https://www.imdb.com/title/tt%s", i.split("/")[1]));
      temp.put("genre", i.split("/")[2]);
      temp.put("title", i.split("/")[0]);
      object.add(temp);
    });

    return object;
  }
}