package com;

import com.csvtomongo.jobs.LinksToMongoJob;
import com.csvtomongo.jobs.MoviesToMongoJob;
import com.csvtomongo.jobs.PostersToMongoJob;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main {

  @Autowired
  private LinksToMongoJob linksToMongoJob;
  @Autowired
  private MoviesToMongoJob moviesToMongoJob;
  @Autowired
  private PostersToMongoJob postersToMongoJob;
  @Autowired
  private static MongoTemplate mongoTemplate;

  public static void main(String[] args) throws IOException {
    SpringApplication.run(Main.class, args);
  }
}
