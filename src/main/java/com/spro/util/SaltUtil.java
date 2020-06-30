package com.spro.util;

import java.util.Random;

/**
 * @description: shiro盐(n位)
 * @package_name: com.spro.util
 * @data: 2020-6-30 15:15
 * @author: Sean
 * @version: V1.0
 */
public class SaltUtil {
    /**
     * 随机六位密码
     * @return
     */
    public static String randomNum(int n) {
        StringBuffer sbfResult = new StringBuffer("");
        char[] strRandomList = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '&', '*', '_' };
        Random random = new Random();
        for (int i = 0; i < n; i++)
        {
            sbfResult.append(strRandomList[random.nextInt(strRandomList.length)]);
        }
        return sbfResult.toString();
    }
}
