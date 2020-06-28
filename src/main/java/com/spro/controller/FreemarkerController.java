package com.spro.controller;

import com.spro.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: freemarkerController控制类
 * @package_name: com.spro.controller
 * @data: 2020-5-21 14:15
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping(value = "freemarkerController")
public class FreemarkerController {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerController.class);

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

    /**
     * 访问index首页
     * @param model
     * @return
     */
    @RequestMapping(value = "toIndex")
    public String toIndex(Model model){
        model.addAttribute("name","Hello Freemarker");
        return "index";
    }

    /**
     * 访问login页面
     * @param model
     * @return
     */
    @RequestMapping(value = "login")
    public String login(Model model){
        return "login";
    }

    /**
     * 访问login页面
     * @param model
     * @return
     */
    @RequestMapping(value = "userAdd")
    public String userAdd(){
        return "userInfo/add";
    }

    /**
     * 访问login页面
     * @param model
     * @return
     */
    @RequestMapping(value = "userUpdate")
    public String userUpdate(){
        return "userInfo/update";
    }

    @RequestMapping(value = "userLogin",method = RequestMethod.POST)
    public String userLogin(String userName,
                            String passWord,
                            Model model){
        logger.info("用户名:"+userName);
        logger.info("密码:"+passWord);

        //1、获取认证主题
        Subject subject = SecurityUtils.getSubject();

        //2、认证token
        AuthenticationToken token = new UsernamePasswordToken(userName,passWord);

        //3、使用shiro执行登录，调用自定义realm
        try{
            subject.login(token);
            //登录成功，跳转至index页面
            model.addAttribute("name",userName);
            return "index";
        } catch (UnknownAccountException e){
            //获取提示错误信息
            model.addAttribute("errMsg","用户名不存在!!");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("errMsg","密码有误!!");
            return "login";
        }
    }

    /**
     * 访问login页面
     * @param model
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(){
        logger.info("退出登录!!");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 访问login页面
     * @param model
     * @return
     */
    @RequestMapping(value = "unAuth",method = RequestMethod.GET)
    public String unAuth(){
        return "unAuth";
    }
}
