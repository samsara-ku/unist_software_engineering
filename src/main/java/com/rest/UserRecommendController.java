package com.rest;

import com.csvtomongo.model.Links;
import com.csvtomongo.model.Movies;
import com.csvtomongo.model.Posters;
import com.csvtomongo.model.RecommendMovie;
import com.csvtomongo.model.RecommendUser;
import com.csvtomongo.repository.LinkRepository;
import com.csvtomongo.repository.MovieRecommendRepository;
import com.csvtomongo.repository.PosterRepository;
import com.csvtomongo.repository.UserRecommendRepository;
import factory.FourArgsFactory;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRecommendController {

  private UserRecommendRepository movieRepository;
  private LinkRepository linkRepository;
  private PosterRepository posterRepository;

  public UserRecommendController(UserRecommendRepository movieRepository,
      LinkRepository linkRepository,
      PosterRepository posterRepository) {
    this.movieRepository = movieRepository;
    this.linkRepository = linkRepository;
    this.posterRepository = posterRepository;
  }

  @Autowired
  private MongoTemplate mongoTemplate;

  @ResponseBody
  @GetMapping("/users/recommendations")
  public Object recommend(@RequestParam("gender") String gender, @RequestParam("age") String age,
      @RequestParam("occupation") String occupation, @RequestParam("genre") String genre, final UserRecommend mediator) {
    FourArgsFactory state = new FourArgsFactory();
    ArrayList<HashMap<String, String>> result = new ArrayList<>();

    if (!state.checkValidity(new String[]{mediator.getGender(), mediator.getAge(),
        mediator.getOccupation(), mediator.getGenre()}).equals("")) {
      String error = state.checkValidity(new String[]{mediator.getGender(), mediator.getAge(),
          mediator.getOccupation(), mediator.getGenre()});

      HashMap<String, String> temp = new HashMap<>();
      temp.put("error", error);

      return temp;
    }

    state = new FourArgsFactory(mediator.getGender(), mediator.getAge(),
        mediator.getOccupation(), mediator.getGenre());


    return getMovieList(state.getTop10());
  }

  public ArrayList<RecommendUser> getMovieList(ArrayList<Integer> movieList){
    ArrayList<RecommendUser> object = new ArrayList<>();

    movieRepository.deleteAll();
    for(int i : movieList){
      Movies movie = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(i)), Movies.class);
      Links link = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(i)), Links.class);
      Posters poster = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(i)), Posters.class);

      RecommendUser RecommendUser = new RecommendUser();
      RecommendUser.setId(movie.getId());
      RecommendUser.setGenre(movie.getGenre());
      RecommendUser.setTitle(movie.getTitle());
      RecommendUser.setLink(link.getLink());
      if(poster == null){
        RecommendUser.setPoster("https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003");
      }
      else {
        RecommendUser.setPoster(poster.getPoster());
      }
      movieRepository.save(RecommendUser);
      object.add(RecommendUser);
    }
    return object;
  }
}
