package com.csvtomongo.repository;

import com.csvtomongo.model.Movies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movies, String> {

}
