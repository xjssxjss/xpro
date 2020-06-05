package com.spro.service.au;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spro.entity.au.User;
import com.spro.enums.ResultCode;
import com.spro.service.base.BaseService;
import com.spro.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 用户登录实现类
 * @package_name: com.spro.service.au
 * @data: 2020-6-5 9:08
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class LoginServiceImpl extends BaseService<User>{

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * 用户登录
     * @return
     */
    public Map<String,Object> login(String requestParam){
        JSONObject object = JSON.parseObject(requestParam);
        Map<String,Object> paramMap = JSON.parseObject(object.getString("param"), HashMap.class);

        //通过对象获取用户名，密码get
        String username = paramMap.get("username").toString();
        String password = paramMap.get("password").toString();

        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("userLoginName",username);
        queryParam.put("userLoginPwd",password);
        try {
            List<User> list = queryByParams(queryParam);
            if(null != list && list.size()>0){
                User user = list.get(0);
                if(user.getValid()){
                    resultMap.put("data",user);
                    resultMap.put("token", UUID.randomUUID().toString());
                    data = resultMap;
                    state = 300;
                } else {
                    state = 301;
                }
            } else {
                state = 302;
            }
        } catch (Exception e) {
            state = 500;
        }
        message = ResultCode.getMessageByStateCode(state);
        return result();
    }
}
