package com.csvtomongo.repository;

import com.csvtomongo.model.RecommendMovie;
import com.csvtomongo.model.RecommendUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecommendRepository extends MongoRepository<RecommendUser, String> {

}
