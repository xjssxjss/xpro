package com.spro.common;

import com.spro.util.PropertiesUtil;

import java.util.Map;

/**
 * @description: TODO
 * @package_name: com.spro.common
 * @data: 2020-5-22 11:08
 * @author: Sean
 * @version: V1.0
 */
public class BaseObject {
    //获取ResourceBox的实例对象
    public static Map<String,String> resourceMap = PropertiesUtil.propertiesMap;
}
