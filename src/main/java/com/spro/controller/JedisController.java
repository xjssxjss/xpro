package com.spro.controller;

import com.spro.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description: TODO
 * @package_name: com.spro.controller
 * @data: 2020-5-21 15:06
 * @author: Sean
 * @version: V1.0
 */

@RestController
@RequestMapping(value = "jedisController")
public class JedisController {
    private static Logger logger = LoggerFactory.getLogger(JedisController.class);

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "randomSetsList")
    public Map<String,Object> randomSetsList(){
        logger.info("randomSetsList>>>>>>>>>>>>>>>>>>>>>>>>>>>.");

        redisUtil.sGet("sList");
        return null;
    }

    /**
     * 获取游戏set集合数据
     * @return
     */
    @RequestMapping(value = "getSetsList")
    public String getSetsList(){
        Set<Object> sList = redisUtil.sGet("sList");
        StringBuffer sb = new StringBuffer();
        Iterator<Object> iterator = sList.iterator();
        while(iterator.hasNext()){
            sb.append(iterator.next());
            sb.append(",");
        }
        String result = "";
        if(!"".equals(sb.toString())){
            result = sb.substring(0, sb.lastIndexOf(","));
        }
        return result;
    }

    /**
     * 删除某一个数据
     * @return
     */
    @RequestMapping(value = "delSets")
    public Object delSets(HttpServletRequest request,
                          HttpServletResponse response) throws InterruptedException {
        Object spop = redisUtil.spop("sList");
        logger.info("spop>"+spop);
        return spop;
    }

    /**
     * 重置游戏setsList
     * @return
     */
    @RequestMapping(value = "resetSetsList")
    public String resetSetsList(HttpServletRequest request){

        Integer setNum = Integer.parseInt(request.getParameter("setNum"));
        //删除key
        redisUtil.del("sList");


        for(int i=0;i<setNum;i++){
            redisUtil.sSet("sList", (i+1)+"");
        }
        return "success";
    }

}
