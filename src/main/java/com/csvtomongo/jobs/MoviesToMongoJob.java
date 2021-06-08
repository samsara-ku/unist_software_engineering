package com.csvtomongo.jobs;

import com.csvtomongo.model.Movies;
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
public class MoviesToMongoJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Bean
  public Job readMoviesFile() {
    return jobBuilderFactory.get("readMoviesFile").incrementer(new RunIdIncrementer()).start(step1())
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1").<Movies, Movies>chunk(10).reader(moviesReader())
        .writer(moviesWriter()).build();
  }

  @Bean
  public FlatFileItemReader<Movies> moviesReader() {
    FlatFileItemReader<Movies> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("movies_corrected.csv"));
    reader.setLineMapper(new DefaultLineMapper<Movies>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"id", "title", "genre"});

      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Movies>() {{
        setTargetType(Movies.class);
      }});
    }});

    return reader;
  }

  @Bean
  public MongoItemWriter<Movies> moviesWriter() {
    MongoItemWriter<Movies> writer = new MongoItemWriter<Movies>();
    writer.setTemplate(mongoTemplate);
    writer.setCollection("movies");
    return writer;
  }
}