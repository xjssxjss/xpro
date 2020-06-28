package com.spro.config.shiro;

import com.kangyonggan.shiro.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: freemarker整合shiro标签配置
 * @package_name: com.spro.config.shiro
 * @data: 2020-6-28 15:44
 * @author: Sean
 * @version: V1.0
 */
@Component
public class PlatformFreemarkerConfiguer implements InitializingBean {

    @Autowired
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws Exception {
        //加上这句话，可以在页面中使用shiro标签
        configuration.setSharedVariable("shiro",new ShiroTags());
    }

}
