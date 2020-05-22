package com.spro.util;

import com.spro.common.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * key生成器
 */
public class RedisKeyBuilderUtil {
    private static Logger logger = LoggerFactory.getLogger(RedisKeyBuilderUtil.class);
    /**
     * 生成redis key名称 模块:func:args
     * PRO:USERTBL:UID:1001
     * @param module
     * @param func
     * @param args
     * @return
     */
    public static String keyBuilder(String module,String func,String... args){
        //项目前缀
        StringBuffer key = new StringBuffer(GlobalConstant.KEY_PREFIX.toUpperCase());//KEY_PREFIX 为项目前缀

        //追加:
        key.append(GlobalConstant.KEY_SPLIT_CHAR).append(module.toUpperCase()).append(GlobalConstant.KEY_SPLIT_CHAR).append(func.toUpperCase());
        for (String arg : args) {// KEY_SPLIT_CHAR 为分割字符
            key.append(GlobalConstant.KEY_SPLIT_CHAR).append(arg);
        }
        logger.info("builderKey>>"+key.toString());
        return key.toString();
    }

    public static void main(String args[]){
        System.out.println(keyBuilder("usertbl","uid","1001"));;
    }
}
