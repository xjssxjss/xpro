package com.spro.service.ws;

import javax.jws.WebService;

/**
 * @description: 天气webservice服务接口
 * @package_name: com.spro.service.ws
 * @data: 2020-6-22 17:23
 * @author: Sean
 * @version: V1.0
 */
@WebService
public interface WeatherService {
    String getWeatherInfo(String cityName);
}
