package com;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import factory.MovieRecommendFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieRecommendController {

  @ResponseBody
  @GetMapping("/recommend")
    public Object recommend(@RequestBody(required = false) MovieRecommend mediator) {

    MovieRecommendFactory movieRecommendFactory = new MovieRecommendFactory(mediator.getTitle(), mediator.getLimit());
    boolean titleIsValid = movieRecommendFactory.titleIsValid();
    boolean limitIsValid = movieRecommendFactory.limitIsValid();

    if(!titleIsValid || !limitIsValid){
      return movieRecommendFactory.getErrorMessage();
    }

    ArrayList<String> object = new ArrayList<>();

    movieRecommendFactory.getResult().forEach(i -> {
      Map<String, String> temp = new HashMap<>();
      temp.put("imdb", String.format("https://www.imdb.com/title/tt%s", i.split("/")[1]));
      temp.put("genre", i.split("/")[2]);
      temp.put("title", i.split("/")[0]);
      ObjectMapper mapper = new ObjectMapper();
      try {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(temp);
        object.add(json);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    return object;
  }
}