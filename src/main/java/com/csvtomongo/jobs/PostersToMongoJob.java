package com.csvtomongo.jobs;

import com.csvtomongo.model.Posters;
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
public class PostersToMongoJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Bean
  @Order(3)
  public Job readPostersFile() {
    return jobBuilderFactory.get("readPostersFile").incrementer(new RunIdIncrementer()).start(step3())
        .build();
  }

  @Bean
  public Step step3() {
    return stepBuilderFactory.get("step3").<Posters, Posters>chunk(10).reader(PostersReader())
        .writer(PostersWriter()).build();
  }

  @Bean
  public FlatFileItemReader<Posters> PostersReader() {
    FlatFileItemReader<Posters> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("movie_poster.csv"));
    reader.setLineMapper(new DefaultLineMapper<Posters>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"id", "poster"});

      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Posters>() {{
        setTargetType(Posters.class);
      }});
    }});


    return reader;
  }

  @Bean
  public MongoItemWriter<Posters> PostersWriter() {
    MongoItemWriter<Posters> writer = new MongoItemWriter<Posters>();
    writer.setTemplate(mongoTemplate);
    writer.setCollection("posters");
    return writer;
  }
}