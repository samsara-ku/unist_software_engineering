package com.csvtomongo.repository;

import com.csvtomongo.model.RecommendMovie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRecommendRepository extends MongoRepository<RecommendMovie, String> {

}
