package com.spro.config.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 用户登陆Realm
 * @package_name: com.spro.config.shiro.realm
 * @data: 2020-6-23 11:29
 * @author: Sean
 * @version: V1.0
 */
public class UserRealm extends AuthorizingRealm{

    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("执行授权逻辑");
        return null;
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
        String passWord = "1234";

        //2、获取用户名token信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        //3、对比用户名是否相同
        if(!userName.equals(token.getUsername())){
            return null;
        }
        return new SimpleAuthenticationInfo("",passWord,"");
    }
}
