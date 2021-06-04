package com.rest;


import com.csvtomongo.model.Links;
import com.csvtomongo.model.Movies;
import com.csvtomongo.model.Posters;
import com.csvtomongo.model.RecommendMovie;
import com.csvtomongo.repository.LinkRepository;
import com.csvtomongo.repository.MovieRecommendRepository;
import com.csvtomongo.repository.PosterRepository;
import factory.MovieRecommendFactory;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendController {

  private MovieRecommendRepository movieRepository;
  private LinkRepository linkRepository;
  private PosterRepository posterRepository;

  public MovieRecommendController(MovieRecommendRepository movieRepository,
      LinkRepository linkRepository,
      PosterRepository posterRepository) {
    this.movieRepository = movieRepository;
    this.linkRepository = linkRepository;
    this.posterRepository = posterRepository;
  }

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

    return getMovieList(movieRecommendFactory.getResult());
  }


  public ArrayList<RecommendMovie> getMovieList(ArrayList<Integer> movieList){
    ArrayList<RecommendMovie> object = new ArrayList<>();

    movieRepository.deleteAll();
    for(int i : movieList){
      Movies movie = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(i)), Movies.class);
      Links link = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(i)), Links.class);
      Posters poster = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(i)), Posters.class);

      RecommendMovie recommendMovie = new RecommendMovie();
      recommendMovie.setId(movie.getId());
      recommendMovie.setGenre(movie.getGenre());
      recommendMovie.setTitle(movie.getTitle());
      recommendMovie.setLink(link.getLink());
      if(poster == null){
        recommendMovie.setPoster("https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003");
      }
      else {
        recommendMovie.setPoster(poster.getPoster());
      }
      movieRepository.save(recommendMovie);
      object.add(recommendMovie);
    }
    return object;
  }

}