package com.spro.service.au;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spro.entity.au.Menu;
import com.spro.entity.au.Role;
import com.spro.entity.au.RoleMenu;
import com.spro.enums.ResultCode;
import com.spro.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 角色实现类
 * @package_name: com.spro.service.au
 * @data: 2020-6-5 9:08
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseService<Role> {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private MenuServiceImpl menuService;

    @Autowired
    private RoleMenuServiceImpl roleMenuService;

    /**
     * 获取所有角色信息
     * @return
     */
    public Map<String,Object> getAllRoles(String requestParam){
        JSONObject object = JSON.parseObject(requestParam);

        HashMap param = JSON.parseObject(object.getString("param"), HashMap.class);

        try {
            //roles
            List<Role> roles = queryByParams(param);
            //roles to menu
            for(int i = 0 ;i<roles.size();i++){
                logger.info("role:"+roles.get(i));
            }

            //role menu
            List allRoleMenus = roleMenuService.getAllRoleMenus();

            logger.info("AllRoleMenus"+allRoleMenus);

            Map<String, Object> menuList = menuService.getMenus();
            //get all menu

            data = roles;
            state = 200;
        } catch (Exception e) {
            e.printStackTrace();
            state = 500;
        }
        message = ResultCode.getMessageByStateCode(state);
        return result();
    }


    /**
     * 获取所有角色信息
     * @return
     */
    public Map<String,Object> getAllRoleMenu(String requestParam){
        JSONObject object = JSON.parseObject(requestParam);

        HashMap param = JSON.parseObject(object.getString("param"), HashMap.class);

        try {
            List<Role> newList = new ArrayList<>();
            //roles
            List<Role> roles = queryByParams(param);

            //role menu
            List<RoleMenu> allRoleMenus = roleMenuService.getAllRoleMenus();

            //roles to menu
            Map<String, Object> menuList = menuService.getMenus();
            List<Menu> listMenu = (List<Menu>)menuList.get("data");

            for(int i = 0 ;i<roles.size();i++){
                List<Menu> childRoleMenu = null;
                logger.info("role:"+roles.get(i));
                for(int j=0;j<allRoleMenus.size();j++){
                    logger.info("role"+roles.get(i).getId());
                    if(roles.get(i).getId().equals(allRoleMenus.get(j).getRoleIs())){
                        logger.info("listMenu"+listMenu);
                        for(int k=0;k<listMenu.size();k++){
                            if(listMenu.get(k).getId().equals(allRoleMenus.get(j).getMenuIs())){
                                childRoleMenu = menuService.getMenuByMenuId(allRoleMenus.get(j).getMenuIs());
                            }
                        }
                    }
                }
                roles.get(i).setChildren(childRoleMenu);
            }

            logger.error("newRoleInfo"+roles);

            logger.info("AllRoleMenus"+allRoleMenus);

            data = roles;
            state = 200;
        } catch (Exception e) {
            e.printStackTrace();
            state = 500;
        }
        message = ResultCode.getMessageByStateCode(state);
        return result();
    }
}
