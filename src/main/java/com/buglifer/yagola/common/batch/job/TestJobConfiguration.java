package com.buglifer.yagola.common.batch.job;

import com.buglifer.yagola.common.batch.service.BatchService;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TestJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchService batchService;
    private final RestaurantRepository restaurantRepository;

    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep3())
                .build();
    }

    @Bean
    @Scheduled(cron = "5 * * * * *")
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
                    List<RestaurantDTO> restaurantDTOList = batchService.getRestaurants();
                    restaurantDTOList.forEach(
                            e -> log.info("[RestaurantDTO] " + e.toString())
                    );
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
