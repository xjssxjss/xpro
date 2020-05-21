package com.spro.controller;

import com.spro.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "randomSetsList")
    public Map<String,Object> randomSetsList(){
        logger.info("randomSetsList>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        JedisUtil instance = JedisUtil.getInstance();
        JedisUtil.Sets sets = instance.new Sets();
        Set<String> sList = sets.smembers("sList");

        logger.info("sList"+sList);
        return null;
    }

    /**
     * 获取游戏set集合数据
     * @return
     */
    @RequestMapping(value = "getSetsList")
    public String getSetsList(){
        JedisUtil jedisUtils = JedisUtil.getInstance();
        JedisUtil.Sets sets = jedisUtils.new Sets();
        Set<String> set = (Set<String>)sets.smembers("sList");
        StringBuffer sb = new StringBuffer();
        Iterator<String> iterator = set.iterator();
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
    public String delSets(){
        JedisUtil jedisUtils = JedisUtil.getInstance();
        JedisUtil.Sets sets = jedisUtils.new Sets();
        String spop = sets.spop("sList");
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
        //重置数据
        JedisUtil jedisUtils = JedisUtil.getInstance();

        JedisUtil.Keys keyObj = jedisUtils.new Keys();
        //删除key
        keyObj.del("sList");

        JedisUtil.Sets sets = jedisUtils.new Sets();
        for(int i=0;i<setNum;i++){
            sets.sadd("sList", (i+1)+"");
        }
        return "success";
    }

}
