package com.spro.controller.au;

import com.spro.common.BaseController;
import com.spro.service.au.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 用户登录控制层
 * @package_name: com.spro.controller
 * @data: 2020-6-5 9:07
 * @author: Sean
 * @version: V1.0
 */
@RestController
@RequestMapping(value = "loginController")
public class LoginController extends BaseController{

    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestBody String param){
        return getResultMap(loginService.login(param));
    }

}
