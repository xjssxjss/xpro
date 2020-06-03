package com.spro.controller;

import com.spro.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: TODO
 * @package_name: com.spro.controller
 * @data: 2020-5-21 14:15
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping(value = "freemarkerController")
public class FreemarkerController {

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 测试freemarker是否可用
     * @param model
     */
    @RequestMapping(value = "fkIndex")
    public String fkIndex(Model model){
        System.out.println(redisUtil.sGet("XPRO:JEDIS-KEY:SET"));
        model.addAttribute("sList",redisUtil.sGet("XPRO:JEDIS-KEY:SET"));
        return "fkIndex";
    }
}
