package io.github.lafsdev.webflux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class CustomConfiguration {

    @Value("${spring.datasource.maximum-pool-size}")
    private int connectionPools;

    @Bean
    public Scheduler jdbcScheduler(){
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPools));
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
}
