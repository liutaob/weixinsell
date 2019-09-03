/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态枚举
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements CodeEnum {
    /**
     * 支付状态
     */
    WAIT(0, "等待支付"), SUCCESS(1, "支付成功");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String message;

//    PayStatusEnum(Integer code, String message) {
//        this.code = code;
//        this.message = message;
//    }
}
