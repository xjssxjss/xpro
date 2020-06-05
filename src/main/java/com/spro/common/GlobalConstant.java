package com.spro.common;

/**
 * 常量配置
 */
public class GlobalConstant {
    /**
     * 提示信息
     */
    public static String ERROR_MESSAGE = "数据获取失败!!";

    public static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=UTF-8";

    public static String KEY_PREFIX = "xpro";   //项目前缀

    public static String KEY_SPLIT_CHAR = ":";  //redis key分割符

    public static String PROP_SUFFIX = ".properties";   //文件常用后缀

    public static final String BASE_FILE_NAMES = "email";   //所有配置文件名称,多个中间以,逗号进行分隔
}
