package com.rest;

import com.csvtomongo.model.IndexMovie;
import com.csvtomongo.model.Links;
import com.csvtomongo.model.Movies;
import com.csvtomongo.model.Posters;
import com.csvtomongo.model.RecommendMovie;
import com.csvtomongo.repository.IndexMovieRepository;
import com.csvtomongo.repository.LinkRepository;
import com.csvtomongo.repository.MovieRecommendRepository;
import com.csvtomongo.repository.PosterRepository;
import factory.MovieRecommendFactory;
import factory.rating.RatingUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  private IndexMovieRepository movieRepository;
  private LinkRepository linkRepository;
  private PosterRepository posterRepository;

  public IndexController(IndexMovieRepository movieRepository,
      LinkRepository linkRepository,
      PosterRepository posterRepository) {
    this.movieRepository = movieRepository;
    this.linkRepository = linkRepository;
    this.posterRepository = posterRepository;
  }

  @Autowired
  private MongoTemplate mongoTemplate;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Object index() {
    RatingUtils ratingUtils = new RatingUtils();
    ratingUtils.getMovieRankingList(10);

    return getMovieList(ratingUtils.getMovieRankingList(10));
  }


  public ArrayList<IndexMovie> getMovieList(List<Entry<String, Double>> movieList) {
    ArrayList<IndexMovie> object = new ArrayList<>();
    movieRepository.deleteAll();
    for(Entry<String, Double> e : movieList){
      int id = Integer.parseInt(e.getKey());

      Movies movie = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Movies.class);
      Links link = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Links.class);
      Posters poster = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Posters.class);

      IndexMovie indexMovie = new IndexMovie();
      indexMovie.setId(movie.getId());
      indexMovie.setGenre(movie.getGenre());
      indexMovie.setTitle(movie.getTitle());
      indexMovie.setLink(link.getLink());
      indexMovie.setRating(e.getValue());
      if(poster == null){
        indexMovie.setPoster("https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003");
      }
      else {
        indexMovie.setPoster(poster.getPoster());
      }
      movieRepository.save(indexMovie);
      object.add(indexMovie);
    }
    return object;
  }


}
