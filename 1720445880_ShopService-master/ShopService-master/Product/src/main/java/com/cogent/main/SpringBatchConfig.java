package com.cogent.main;

import java.io.File;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SpringBatchConfig {
	
	private final ProductRepository productRepository;
	
	@StepScope
	@Bean
	public FlatFileItemReader<ProductEntity> itemReader(){
		FlatFileItemReader<ProductEntity> read = new FlatFileItemReader<ProductEntity>();
		read.setResource(new FileSystemResource(new File("src/main/resources/product.csv")));
		read.setName("CSV-Reader");
		read.setLinesToSkip(1);
		read.setLineMapper(lineMapper());
		return read;
	}
	LineMapper<ProductEntity> lineMapper() {
		DefaultLineMapper<ProductEntity> lineMapper = new DefaultLineMapper<ProductEntity>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "name", "description", "price", "category", "imageUrl");

		BeanWrapperFieldSetMapper<ProductEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<ProductEntity>();
		fieldSetMapper.setTargetType(ProductEntity.class);

		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.setLineTokenizer(lineTokenizer);
		return lineMapper;
	}

	@Bean
	public ProductProcessor processor() {
		return new ProductProcessor();
	}

	@Bean
	public RepositoryItemWriter<ProductEntity> writer() {
		RepositoryItemWriter<ProductEntity> writer = new RepositoryItemWriter<ProductEntity>();
		writer.setRepository(productRepository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public Step step(ItemReader<ProductEntity> reader, ItemProcessor<ProductEntity, ProductEntity> processor,
			ItemWriter<ProductEntity> writer, JobRepository jobRepository, PlatformTransactionManager manager) {
		return new StepBuilder("mystep", jobRepository).<ProductEntity, ProductEntity>chunk(10, manager).reader(reader)
				.processor(processor).writer(writer).build();
	}

	@Bean
	public Job runJob(Step step, JobRepository jobRepository) {
		return new JobBuilder("myjob", jobRepository).incrementer(new RunIdIncrementer()).flow(step).end().build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.setConcurrencyLimit(10);
		return taskExecutor;
	}

}
