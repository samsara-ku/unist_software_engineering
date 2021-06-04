package com.csvtomongo.repository;

import com.csvtomongo.model.Posters;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends MongoRepository<Posters, String> {

}
