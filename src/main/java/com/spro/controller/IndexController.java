package com.spro.controller;

import com.spro.util.EmailSender;
import com.spro.common.GlobalConstant;
import com.spro.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/sendEmail",method = RequestMethod.GET)
    public void sendEmail(){
        try {
            EmailSender.sendMailAttachment("xieyahui@wondersgroup.com","测试邮件标题","邮件内容",new String[]{"e:\\每周计划.emmx"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载模板
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
    public void downLoadDisToothBrushExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String downloadPath = "D:\\GIT\\spro\\src\\main\\webapp\\download\\template\\";
        String contentType = GlobalConstant.CONTENT_TYPE;
        //String fileName = downLoadName;
        //FileUtil.download(request, response, path + "\\", fileName, contentType, fileName);
    }

}
