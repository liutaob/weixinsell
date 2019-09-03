/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品是否在架状态枚举
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum {
    /**
     * UP 0在架、DOWN 1下架
     */
    UP(0, "在架"), DOWN(1, "下架");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

//    ProductStatusEnum(Integer code, String message) {
//        this.code = code;
//        this.message = message;
//    }

}
