package com.spro.service.ws;

import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @description: 天气webservice服务接口实现
 * @package_name: com.spro.service.ws
 * @data: 2020-6-22 17:23
 * @author: Sean
 * @version: V1.0
 */
@WebService
@Component
public class WeatherServiceImpl implements WeatherService {

    /**
     * 获取城市天气信息
     * @param cityName
     * @return
     */
    @Override
    public @WebResult(name="getWeatherInfoResult") String getWeatherInfo(@WebParam(name = "cityName") String cityName) {
        return "有你，便是晴天!!";
    }

}
