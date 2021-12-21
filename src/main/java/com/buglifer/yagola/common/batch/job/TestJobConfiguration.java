package com.buglifer.yagola.common.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TestJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep1())
                .build();
    }

    @Bean
    public Step testStep1() {
        return stepBuilderFactory.get("testStep1")
                .tasklet((contribution, chunkContext) ->{
                    log.info("[TestJob] TestStep 1 !!!!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
