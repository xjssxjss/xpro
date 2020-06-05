package com.spro.listener;

import com.spro.common.GlobalConstant;
import com.spro.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(PropertiesListener.class);
    private String propertyFileName;

    public PropertiesListener() {
        this.propertyFileName = GlobalConstant.BASE_FILE_NAMES;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info("load properties数据begin>>>>>>>>>>>>>>>>>>>>");
        //把所有指定文件中的key，value放入缓存map中
        PropertiesUtil.loadAllProperties(propertyFileName);
        logger.info("load properties数据end>>>>>>>>>>>>>>>>>>>>>>");
    }
}
