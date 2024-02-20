package com.pharos.common.threadpool;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wcj
 * @version 1.0
 * @description: 线程池配置
 * @date 2023/8/31 7:49 PM
 */
@Configuration
public class TaskExecutePoolConfig {

    @Bean("sendEmailThreadPool")
    public Executor sendEmailThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("sendEmailThreadPool");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return TtlExecutors.getTtlExecutor(executor);

    }

}
