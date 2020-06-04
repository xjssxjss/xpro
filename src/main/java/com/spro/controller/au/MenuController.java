package com.spro.controller.au;

import com.spro.common.BaseController;
import com.spro.service.au.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 菜单控制类
 * @package_name: com.spro.controller.au
 * @data: 2020-6-3 10:21
 * @author: Sean
 * @version: V1.0
 */
@RestController
@RequestMapping(value = "menuController")
@EnableSwagger2
public class MenuController extends BaseController{

    @Autowired
    private MenuServiceImpl menuService;

    /**
     * 获取菜单
     * @return
     */
    @RequestMapping(value = "getMenus",method = RequestMethod.GET)
    public Map<String,Object> getMenus(){
        return getResultMap(menuService.getMenus());
    }

    /**
     * 获取权限列表数据
     * @return
     */
    @RequestMapping(value = "getMenuListInfo",method = RequestMethod.POST)
    public Map<String,Object> getMenuListInfo(@RequestBody String param){
        return getResultMap(menuService.getMenuListInfo(param));
    }
}
