package com.rest;

import com.csvtomongo.model.Links;
import com.csvtomongo.model.Movies;
import com.csvtomongo.model.Posters;
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
    for(Entry<Integer, Double> e : movieList){
      int id = e.getKey();

      Movies movie = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Movies.class);
      Links link = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Links.class);
      Posters poster = mongoTemplate.findOne(
          Query.query(Criteria.where("id").is(id)), Posters.class);

      Movies indexMovie = new Movies();
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
      mongoTemplate.save(indexMovie, collection);
      object.add(indexMovie);
    }
    return object;
  }
}
