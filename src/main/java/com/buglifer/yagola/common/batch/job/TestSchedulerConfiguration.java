package com.buglifer.yagola.common.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class TestSchedulerConfiguration {

    JobLauncher jobLauncher;
    TestJobConfiguration jobConfiguration;

    @Scheduled(cron = "5 * * * * *")
    public void testScheduler() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder().addDate("time", new Date()).toJobParameters();
        jobLauncher.run(jobConfiguration.testRestaurant(), jobParameters);
    }

}
