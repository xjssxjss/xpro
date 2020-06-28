package com.spro.config.shiro;

import com.spro.config.shiro.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: Shiro配置管理器
 * @package_name: com.spro.config.shiro
 * @data: 2020-6-23 11:28
 * @author: Sean
 * @version: V1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean工厂
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        /**
         * 设置安全管理器
         */
        filterFactoryBean.setSecurityManager(securityManager);

        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用过滤器：
         *      anon:无需认证（登录）可以访问，
         *      authc:需要认证才能访问
         *      user:如果使用rememberMe的功能可以直接访问
         *      perms:该资源必须得到资源权限才能访问
         *      role:该资源必须得到角色资源权限才能访问
         */
        /**
         * 设置拦截路径Map
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/freemarkerController/login","anon");
        //提交登录按钮不拦截
        filterMap.put("/freemarkerController/userLogin","anon");

        filterMap.put("/freemarkerController/*","authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        /**
         * 设置跳转login页面
         */
        filterFactoryBean.setLoginUrl("/freemarkerController/login");
        return filterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /**
         * 设置自定义Realm
         */
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 引入自定义Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
