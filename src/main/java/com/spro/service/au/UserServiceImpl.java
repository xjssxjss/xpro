package com.spro.service.au;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spro.entity.au.User;
import com.spro.enums.ResultCode;
import com.spro.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户信息实现类
 * @package_name: com.spro.service.au
 * @data: 2020-6-4 9:18
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService<User>{
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 获取所有用户信息
     * @return
     */
    public Map<String,Object> getUserInfo(String requestParam){
        JSONObject object = JSON.parseObject(requestParam);
        Map<String,Object> paramMap = JSON.parseObject(object.getString("param"), HashMap.class);

        try {
            startPage(paramMap);
            List<User> userList = queryByParams(paramMap);

            //获取数据中数据总数，用于分页使用
            int total = queryCountByParams(paramMap);

            result.put("total",total);
            result.put("userList",userList);
            data = result;
            state = 200;
        } catch (Exception e) {
            e.printStackTrace();
            state = 501;
        }
        message = ResultCode.getMessageByStateCode(state);
        return result();
    }

    /**
     * 更新用户信息
     * @param param
     * @return
     */
    public Map<String,Object> editUserInfo(String requestParam){
        JSONObject object = JSON.parseObject(requestParam);
        User user = JSON.parseObject(object.getString("param"), User.class);

        try {
            int update = updateByPrimaryKeySelective(user);
            if(update>0){
                logger.info("影响条数:"+update);
                state = 200;
            }
        } catch (Exception e) {
            state = 501;
        }
        message = ResultCode.getMessageByStateCode(state);

        return result();
    }

}
