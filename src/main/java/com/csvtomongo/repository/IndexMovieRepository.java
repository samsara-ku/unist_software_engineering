package com.csvtomongo.repository;

import com.csvtomongo.model.IndexMovie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexMovieRepository extends MongoRepository<IndexMovie, String> {

}
