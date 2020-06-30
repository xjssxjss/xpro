package com.spro.config.shiro.realm;


import com.spro.service.au.LoginServiceImpl;
import com.spro.service.au.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 用户登陆Realm
 * @package_name: com.spro.config.shiro.realm
 * @data: 2020-6-23 11:29
 * @author: Sean
 * @version: V1.0
 */
public class UserRealm extends AuthorizingRealm{

    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //模拟用户名密码
        //1、获取数据库用户名，密码信息
        String userName = "admin";
        String passWord = "6313099f894325afea1fb736971aade2";
        String salt = "z7MkAP";

        if("admin".equals(authenticationToken.getPrincipal())){
            return new SimpleAuthenticationInfo(userName,passWord, ByteSource.Util.bytes(salt),this.getName());
        }
        return null;
    }
}
