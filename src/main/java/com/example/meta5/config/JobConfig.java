package com.example.meta5.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.batch.core.configuration.JobBuilderFactory;

@Configuration
//@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
public class JobConfig {

    @Bean
    public Job footballJob(JobRepository jobRepository) {
        return new JobBuilder("footballJob", jobRepository)
                .start(playerLoad())
                .next(gameLoad())
                .next(playerSummarization())
                .build();
    }

    private Step playerSummarization() {
        return null;
    }

    private Step gameLoad() {
        return null;
    }

    private Step playerLoad() {
        return null;
    }
/*
    @Bean
    public Step exampleStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello, Batch!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
*/


}
