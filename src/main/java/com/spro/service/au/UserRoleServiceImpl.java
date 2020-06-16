package com.spro.service.au;

import com.spro.entity.au.User;
import com.spro.entity.au.UserRole;
import com.spro.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @package_name: com.spro.service.au
 * @data: 2020-6-5 15:00
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class UserRoleServiceImpl extends BaseService<UserRole> {

    /**
     * 给用户非配角色
     */
    public void addUserRole(User user,Integer roleId){
        //获取userId
        user.getId();
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(roleId);

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",user.getId());
        //添加之前首先把之前的角色关联信息删除
        try {
            List<UserRole> userRoles = queryByParams(paramMap);
            if(null != userRoles && userRoles.size()>0){
                delete(userRoles.get(0).getId());
            }
            insert(userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
