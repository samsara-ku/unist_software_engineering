package com.csvtomongo.repository;

import com.csvtomongo.model.Links;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Links, String> {

}
