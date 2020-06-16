package com.spro.controller.au;

import com.spro.common.BaseController;
import com.spro.service.au.LoginServiceImpl;
import com.spro.service.au.RoleServiceImpl;
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
@RequestMapping(value = "roleController")
public class RoleController extends BaseController{

    @Autowired
    private RoleServiceImpl roleService;

    /**
     * 获取所有角色信息
     * @return
     */
    @RequestMapping(value = "getAllRoles",method = RequestMethod.POST)
    public Map<String,Object> getAllRoles(@RequestBody String param){
        return getResultMap(roleService.getAllRoles(param));
    }

    /**
     * 获取所有角色信息
     * @return
     */
    @RequestMapping(value = "getAllRoleMenu",method = RequestMethod.POST)
    public Map<String,Object> getAllRoleMenu(@RequestBody String param){
        return getResultMap(roleService.getAllRoleMenu(param));
    }
}
