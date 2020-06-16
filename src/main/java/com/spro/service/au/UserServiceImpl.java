package com.spro.service.au;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spro.entity.au.User;
import com.spro.enums.ResultCode;
import com.spro.service.base.BaseService;
import com.spro.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRoleServiceImpl userRoleService;

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

    /**
     * 校验用户名是否已存在
     * @param userName
     * @return
     */
    public Map<String,Object> checkUserName(String userName) {

        //获取用户名,对用户名判空
        if(!StringUtil.isEmpty(userName)){

            Map<String,Object> param = new HashMap<>();
            param.put("userLoginName",userName);
            List<User> users = null;
            try {
                users = queryByParams(param);
                if(null != users && users.size()>0){
                    state = 206;
                } else {
                    state = 200;
                }
            } catch (Exception e) {
                e.printStackTrace();
                state = 500;
            }

            message = ResultCode.getMessageByStateCode(state);
        }

        return result();
    }

    /**
     * 新增用户方法
     * @param param
     * @return
     */
    public Map<String,Object> addUser(String param) {
        JSONObject object = JSON.parseObject(param);

        User user = JSON.parseObject(object.getString("param"), User.class);
        try {
            //add
            if(null == user.getId()){
                int userId = insert(user);

                Map param1 = JSON.parseObject(object.getString("param"), Map.class);
                logger.info("id:"+param1.get("userId"));
                userRoleService.addUserRole(user,//获取Param
                        Integer.parseInt(JSON.parseObject(object.getString("param")).get("roleId").toString()));
                if(userId > 0){
                    state = 200;
                }
                message = ResultCode.getMessageByStateCode(state);
            } else {
                //edit
                update(user);
                state = 200;
                message = ResultCode.getMessageByStateCode(state);
            }
        } catch (Exception e){
            e.printStackTrace();
            state = 500;
            message = e.getMessage();
        }
        return result();
    }
}
