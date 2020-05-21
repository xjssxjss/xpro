package com.spro.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 */
@Component
public class ScheduledTask {

    Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * 测试定时任务
     * @throws Exception
     */
    @Scheduled(cron = "*/20 * * * * ?")
    public void testScheduledTask() {
        logger.info("spring boot整合定时任务");
    }
}
