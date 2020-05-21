package com.spro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: indexController
 * @package_name: com.spro.controller
 * @data: 2020-5-21 13:43
 * @author: Sean
 * @version: V1.0
 */

@RestController
@RequestMapping(value = "/indexController")
public class IndexController {

    /**
     * spring boot 项目index
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "spring boot 项目学习进阶start>>>>>>";
    }
}
