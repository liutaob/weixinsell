/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 订单表单 验证
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Data
public class OrderForm {

    /**
     * 买家姓名.
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号.
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址.
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家微信openid.
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车. 注意：前端传递的是json字符串
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
