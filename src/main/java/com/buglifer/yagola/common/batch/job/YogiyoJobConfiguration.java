package com.buglifer.yagola.common.batch.job;

import com.buglifer.yagola.common.batch.response.yogiyo.TotalRestaurantResponse;
import com.buglifer.yagola.common.batch.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class YogiyoJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchService batchService;
    private List<TotalRestaurantResponse> totalRestaurantResponseList;

//    @Bean
//    public Job updateRestaurantJob() {
//        return jobBuilderFactory.get("updateRestaurantJob")
//                .start()
//    }

    @Bean
    public Step getTotalRestaurantResponse() {
        return stepBuilderFactory.get("getTotalRestaurantResponse")
                .tasklet((contribution, chunkContext) -> {
                    log.info("[Step] [getTotalRestaurantResponse] Start" );

                    log.info("[Step] [getTotalRestaurantResponse] End");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
