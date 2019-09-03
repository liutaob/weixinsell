/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements CodeEnum{
    /**
     * 订单状态
     */
    NEW(0, "新订单"), FINISHED(1, "完结"), CANCEL(2, "已取消");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String message;

//    OrderStatusEnum(Integer code, String message) {
//        this.code = code;
//        this.message = message;
//    }
}
