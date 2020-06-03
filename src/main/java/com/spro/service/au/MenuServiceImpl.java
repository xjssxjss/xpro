package com.spro.service.au;

import com.spro.entity.au.Menu;
import com.spro.enums.ResultCode;
import com.spro.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 菜单业务类
 * @package_name: com.spro.service.au
 * @data: 2020-6-3 10:04
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class MenuServiceImpl extends BaseService<Menu> {

    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    /**
     * 获取菜单
     */
    public Map<String,Object> getMenus(){
        //新菜单数据集合声明
        List<Menu> listMenu = new ArrayList<>();
        List<Menu> menus = null;
        try {
            menus = queryByParams(null);
            /**
             * 对菜单数据进行遍历
             */
            for (Menu menuObj : menus) {
                //说明是一级菜单
                if(menuObj.getType().equals("M")){
                    menuObj.setChildren(getChildMenus(menuObj,menus));
                    listMenu.add(menuObj);
                }
            }

            state = 200;
            data = listMenu;
            message = ResultCode.getMessageByStateCode(state);
        } catch (Exception e) {
            state = 502;
            message = ResultCode.getMessageByStateCode(state);
        }
        return result();
    }

    /**
     * 通过传入的菜单，获取子菜单
     * @return
     */
    public List<Menu> getChildMenus(Menu menuObj,List<Menu> menuList){
        //声明子菜单集合
        List<Menu> childMenuList = new ArrayList<>();

        for (Menu menu : menuList) {
            //排除id相同，还有不是当前父级菜单子菜单的项
            logger.info("menuObj.getId()"+menuObj.getId()+"menu.getId()"+menu.getId());
            if(menuObj.getId() != menu.getId() && menuObj.getId() == menu.getParentId()){
                //说明为当前对象的菜单对象
                if(null != menu.getParentId()){
                    List<Menu> menuList1 = new ArrayList<>();
                    List<Menu> menuList2 = getChildMenus(menu,menuList);
                    for (Menu menu1 : menuList2) {
                        if(menu.getId() != menu1.getId() && menu.getId() == menu1.getParentId()){
                            //是否设置children为null
                            //如果获取主菜单ID，在parentId中没有映射，则可以设置为null
                            menuList1.add(menu1);
                        }
                    }
                    menu.setChildren(menuList1);
                }
                if(menu.getChildren().size() == 0 ){
                    menu.setChildren(null);
                }
                childMenuList.add(menu);
            }
        }
        return childMenuList;
    }
}
