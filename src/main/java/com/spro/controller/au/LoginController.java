package com.spro.controller.au;

import com.spro.common.BaseController;
import com.spro.service.au.LoginServiceImpl;
import com.spro.util.VerifyCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @description: 用户登录控制层
 * @package_name: com.spro.controller
 * @data: 2020-6-5 9:07
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping(value = "loginController")
public class LoginController extends BaseController{
    
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 获取图片验证码
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "getImage",method = RequestMethod.GET)
    public void getImage(HttpSession session,
                         HttpServletResponse response) throws IOException {
        //实例化对象
        VerifyCodeUtil verifycode = new VerifyCodeUtil();
        //获取对象图片
        BufferedImage image = verifycode.getImage();
        session.setAttribute("textCode", verifycode.text());
        VerifyCodeUtil.output(image, response.getOutputStream());
    }

    /**
     * 用户登录
     * @return
     */
    /*@RequestMapping(value = "login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestBody String param){
        return getResultMap(loginService.login(param));
    }*/

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String userLogin(HttpSession session,
                            String userName,
                            String passWord,
                            String textCode,
                            Model model){
        logger.info("用户名:"+userName);
        logger.info("密码:"+passWord);

        //判断验证码是否正确
        if(textCode.equalsIgnoreCase(session.getAttribute("textCode")+"")){
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
        } else {
            model.addAttribute("errMsg","验证码不匹配!!");
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
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
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

    /**
     * 访问login页面
     * @param model
     * @return
     */
    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }
}
