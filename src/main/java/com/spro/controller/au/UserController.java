package com.spro.controller.au;

import com.spro.common.BaseController;
import com.spro.service.au.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 用户信息控制类
 * @package_name: com.spro.controller.au
 * @data: 2020-6-4 9:22
 * @author: Sean
 * @version: V1.0
 */
@RestController
@RequestMapping(value = "userController")
@EnableSwagger2
public class UserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取用户信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "getUserInfo",method = RequestMethod.POST)
    public Map<String,Object> getUserInfo(@RequestBody String param){
        return getResultMap(userService.getUserInfo(param));
    }

    /**
     * 更新用户信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "editUserInfo",method = RequestMethod.POST)
    public Map<String,Object> editUserInfo(@RequestBody String param){
        return getResultMap(userService.editUserInfo(param));
    }

}
