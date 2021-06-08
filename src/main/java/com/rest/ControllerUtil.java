package com.rest;

import com.csvtomongo.model.Links;
import com.csvtomongo.model.Movies;
import com.csvtomongo.model.Posters;
import com.csvtomongo.model.RecommendMovie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ControllerUtil {

  @Autowired
  private MongoTemplate mongoTemplate;

  public ArrayList<Movies> getMovieList(List<Entry<Integer, Double>> movieList, String collection) {
    ArrayList<Movies> object = new ArrayList<>();
    mongoTemplate.dropCollection(collection);
    for (Entry<Integer, Double> e : movieList) {
      int id = e.getKey();

      Movies movie = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Movies.class);
      Links link = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Links.class);
      Posters poster = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Posters.class);

      RecommendMovie recommendMovie = new RecommendMovie();
      recommendMovie.setId(movie.getId());
      recommendMovie.setGenre(movie.getGenre());
      recommendMovie.setTitle(movie.getTitle());
      recommendMovie.setLink("https://www.imdb.com/title/tt" + link.getLink());
      recommendMovie.setRating(e.getValue());
      if (poster == null) {
        recommendMovie.setPoster(
            "https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003");
      } else {
        recommendMovie.setPoster(poster.getPoster());
      }
      mongoTemplate.save(recommendMovie, collection);
      object.add(recommendMovie);
    }
    return object;
  }
}
