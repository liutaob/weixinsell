/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 插入商品Map作参数 工具类
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Component("maputil")
@Scope("prototype")
public class MapUtil {

    public Object arg2Type(Object o) {
        if (o instanceof String) {
            return o.toString();
        }
        if (o instanceof Integer) {
            return (Integer) o;
        }
        if (o instanceof BigDecimal) {
            return (BigDecimal) o;
        }
        return null;
    }
}
