/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)视图对象 这个是前端和后端共同使用的，需要一致
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Data
@Accessors(chain = true)
public class ProductVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6050055980406503338L;

    /**
     * 商品名 .
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 商品类目 .
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 包含一个商品详情对象.
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
