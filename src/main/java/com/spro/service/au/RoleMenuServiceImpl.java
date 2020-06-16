package com.spro.service.au;

import com.spro.entity.au.RoleMenu;
import com.spro.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: TODO
 * @package_name: com.spro.service.au
 * @data: 2020-6-8 15:02
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends BaseService<RoleMenu> {

    /**
     * get All Role_Menu
     * @return
     */
    public List<RoleMenu> getAllRoleMenus(){
        List<RoleMenu> listRoleMenu = null;
        try {
            listRoleMenu = queryByParams(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRoleMenu;
    }

}
