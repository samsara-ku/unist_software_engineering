package com.csvtomongo.jobs;

import com.csvtomongo.model.Links;
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
public class LinksToMongoJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Bean
  @Order(1)
  public Job readLinksFile() {
    return jobBuilderFactory.get("readLinksFile").incrementer(new RunIdIncrementer()).start(step2())
        .build();
  }

  @Bean
  public Step step2() {
    return stepBuilderFactory.get("step2").<Links, Links>chunk(10).reader(linksReader())
        .writer(linksWriter()).build();
  }

  @Bean
  public FlatFileItemReader<Links> linksReader() {
    FlatFileItemReader<Links> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("links.csv"));
    reader.setLineMapper(new DefaultLineMapper<Links>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"id", "link"});

      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Links>() {{
        setTargetType(Links.class);
      }});
    }});


    return reader;
  }

  @Bean
  public MongoItemWriter<Links> linksWriter() {
    MongoItemWriter<Links> writer = new MongoItemWriter<Links>();
    writer.setTemplate(mongoTemplate);
    writer.setCollection("links");
    return writer;
  }
}