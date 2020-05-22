package com.spro.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: indexController
 * @package_name: com.spro.controller
 * @data: 2020-5-21 13:43
 * @author: Sean
 * @version: V1.0
 */

@RestController
@RequestMapping(value = "/indexController")
@EnableSwagger2
public class IndexController {

    /**
     * spring boot 项目index
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ApiOperation(value = "spring boot入口方法" , notes = "spring boot项目测试入口")
    public String index(){
        return "spring boot 项目学习进阶start>>>>>>";
    }
}
