package com.csvtomongo.jobs;

import com.csvtomongo.model.Ratings;
import com.csvtomongo.model.Ratings;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableBatchProcessing
@Configuration
public class RatingsToMongoJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private MongoTemplate mongoTemplate;

 @Bean
 public Job readRatingsFile() {
   return jobBuilderFactory.get("readRatingsFile").incrementer(new RunIdIncrementer()).start(step4())
       .build();
 }

 @Bean
 public Step step4() {
   return stepBuilderFactory.get("step4").<Ratings, Ratings>chunk(10).reader(ratingsReader())
       .writer(ratingsWriter()).build();
 }

  @Bean
  public FlatFileItemReader<Ratings> ratingsReader() {
    FlatFileItemReader<Ratings> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("ratings.csv"));
    reader.setLineMapper(new DefaultLineMapper<Ratings>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"user", "movie", "rating","time"});

      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Ratings>() {{
        setTargetType(Ratings.class);
      }});
    }});


    return reader;
  }

  @Bean
  public MongoItemWriter<Ratings> ratingsWriter() {
    MongoItemWriter<Ratings> writer = new MongoItemWriter<Ratings>();
    writer.setTemplate(mongoTemplate);
    writer.setCollection("ratings");
    return writer;
  }
}