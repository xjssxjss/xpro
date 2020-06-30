package com.spro.shiro;

import com.spro.util.SaltUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description: shiro测试类
 * @package_name: com.spro.shiro
 * @data: 2020-6-30 15:16
 * @author: Sean
 * @version: V1.0
 */
@SpringBootTest
public class TestShiro {

    /**
     * 初始化设置安全管理器
     */
    @Before
    public void initDefaultSecurityManager(){

    }

    /**
     * shiro初始化密码
     */
    @Test
    public void encodePassword(){
        //使用MD5+salt处理
        String salt = SaltUtil.randomNum(6);
        System.out.println("当前盐:"+salt);
        //source 密码 salt 盐  hashIterations 散列次数
        Md5Hash saltResult = new Md5Hash("Abc123", salt,1024);
        System.out.println(saltResult);
    }

}
