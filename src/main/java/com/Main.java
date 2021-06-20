package com;

import com.csvtomongo.jobs.LinksToMongoJob;
import com.csvtomongo.jobs.MoviesToMongoJob;
import com.csvtomongo.jobs.PostersToMongoJob;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main extends SpringBootServletInitializer{

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

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Main.class);
  }

  @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
