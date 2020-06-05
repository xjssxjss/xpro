package com.spro.common;

import com.spro.util.PropertiesUtil;

import java.util.Map;

/**
 * BaseObject通用类
 */
public class BaseObject {
    //获取ResourceBox的实例对象
    public static Map<String,String> resourceMap = PropertiesUtil.propertiesMap;
}
