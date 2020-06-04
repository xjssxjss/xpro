package com.spro.service.au;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
                if(menuObj.getType().equals("F")){
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
            if(menuObj.getId() != menu.getId() && menuObj.getId() == menu.getParentId() && !menuObj.getType().equals("S")){
                //说明为当前对象的菜单对象
                if(null != menu.getParentId()){
                    List<Menu> menuList1 = new ArrayList<>();
                    List<Menu> menuList2 = getChildMenus(menu,menuList);
                    for (Menu menu1 : menuList2) {
                        if(menu.getId() != menu1.getId() && menu.getId() == menu1.getParentId() && !menuObj.getType().equals("S")){
                            //是否设置children为null
                            //如果获取主菜单ID，在parentId中没有映射，则可以设置为null
                            //判断如果menu1中权限type为3级的话，则添加
                            menuList1.add(menu1);
                        }
                    }
                    menu.setChildren(menuList1);
                }
                if(menu.getChildren().size() == 0 ){
                    menu.setChildren(null);
                }
                if(!"T".equals(menu.getType())){
                    childMenuList.add(menu);
                }
            }
        }
        return childMenuList;
    }

    /**
     * 获取菜单列表数据
     * requestParam
     * @return
     */
    public Map<String,Object> getMenuListInfo(String requestParam){
        JSONObject object = JSON.parseObject(requestParam);
        Map<String,Object> paramMap = JSON.parseObject(object.getString("param"), HashMap.class);

        Map<String,Object> resultMap = new HashMap<>();
        try {
            //开始分页
            startPage(paramMap);
            List<Menu> menuList = queryByParams(paramMap);
            resultMap.put("menuList",menuList);
            resultMap.put("total",queryCountByParams(paramMap));
            data = resultMap;
            state = 200;
        } catch (Exception e) {
            state = 501;
        }
        message = ResultCode.getMessageByStateCode(state);

        return result();
    }
}
