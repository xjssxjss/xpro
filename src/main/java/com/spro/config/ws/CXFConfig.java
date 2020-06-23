package com.spro.config.ws;

import com.spro.service.ws.WeatherService;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


/**
 * @description: web service配置类
 * @package_name: com.spro.config.ws
 * @data: 2020-6-22 17:27
 * @author: Sean
 * @version: V1.0
 */
@Configuration
public class CXFConfig {

    private static Logger logger = LoggerFactory.getLogger(CXFConfig.class);

    @Autowired
    private WeatherService weatherService;


  /*  public ServletRegistrationBean dispatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/soap*//*");
    }*/

    /**
     * 发布服务
     * 指定访问url
     * @return
     */
    @Bean
    public Endpoint weatherEndPoint(){
        logger.info("web service start>>>>>>>>>>>>>>>>>>>>>>>>>>");
        EndpointImpl endpoint = new EndpointImpl(weatherService);
        endpoint.publish("/weather");
        logger.info("web service end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return endpoint;
    }

}
