package com.buglifer.yagola.common.batch.job;

import com.buglifer.yagola.common.batch.service.BatchService;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TestJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchService batchService;
    private final RestaurantRepository restaurantRepository;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "5 * * * * *")
    public void testScheduler() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder().addDate("time", new Date()).toJobParameters();
        jobLauncher.run(testRestaurant(), jobParameters);
    }

    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep3())
                .build();
    }

    @Bean
    public Job testRestaurant() {
        return jobBuilderFactory.get("testRestaurant")
                .start(testStep3())
                .build();
    }

    @Bean
    public Step testStep3() {
        return stepBuilderFactory.get("testStep3")
                .tasklet((contribution, chunkContext) ->{
                    log.info("[TestJob] TestStep 3 !!!!");
                    long seq = 1;
                    Optional<RestaurantEntity> optionalEntity = restaurantRepository.findById(seq);
                    if(!optionalEntity.isEmpty()) {
                        RestaurantEntity entity = optionalEntity.get();
                        RestaurantDTO dto = RestaurantDTO.fromEntity().entity(entity).build();
                        dto.setStartTime(LocalTime.now());
                        RestaurantEntity testEntity = RestaurantEntity.initRestaurant().dto(dto).build();
                        restaurantRepository.save(testEntity);
                    }

                    List<RestaurantDTO> restaurantDTOList = batchService.getRestaurants();
                    restaurantDTOList.forEach(
                            e -> log.info("[RestaurantDTO] " + e.toString())
                    );
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
