package com.jxust.sell.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * 解决druid不自动解密
 */
@Slf4j
public class DbPasswordCallback extends DruidPasswordCallback {

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = (String) properties.get("config.decrypt.password");
        String publickey = (String) properties.get("config.decrypt.key");
        try {
            String dbpassword = ConfigTools.decrypt(publickey, password);
            setPassword(dbpassword.toCharArray());
        } catch (Exception e) {
            log.error("Druid ConfigTools.decrypt", e);
        }
    }
}