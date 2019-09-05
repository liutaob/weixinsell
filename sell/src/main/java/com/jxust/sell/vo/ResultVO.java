/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * http请求返回的最外层json对象
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Data
@Accessors(chain = true)
public class ResultVO<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4677857050959012953L;

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
//    private String msg = "";
    private String msg;

    /**
     * 具体内容.
     */
    private T data;// 这个是通用的返回，需要使用泛型
}