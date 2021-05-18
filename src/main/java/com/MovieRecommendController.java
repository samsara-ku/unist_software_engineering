package com;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import factory.link.LinkUserAndRating;
import factory.rating.MovieTitleRecommend;
import factory.user.LoadMovieTitle;
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
  public Object recommend(@RequestBody final MovieRecommend mediator) {
    LoadMovieTitle aa = new LoadMovieTitle(mediator.getTitle());
    MovieTitleRecommend bb  = new MovieTitleRecommend(aa.getUserList(), Integer.parseInt(mediator.getLimit()), aa.getMovieId(),aa.getMovieGenre());
    LinkUserAndRating result = new LinkUserAndRating(bb.getTopRecommendMovies(),Integer.parseInt(mediator.getLimit()));
    ArrayList<String> topMovies = result.getLinkList();
    ArrayList<String> object = new ArrayList<>();

    topMovies.forEach(i -> {
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
