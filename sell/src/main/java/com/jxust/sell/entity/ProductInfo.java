/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.entity;

import com.jxust.sell.enums.ProductStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Entity
@Data
@DynamicUpdate
@Accessors(chain = true)
public class ProductInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1441872166231127730L;

    /*Integer有上限，采用字符串*/
    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0(正常),1(下架)，默认是上架状态. */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    private Integer categoryType;

//    /** 创建时间. */
//    private Date createTime;
//
//    /** 更新时间. */
//    private Date updateTime;
//
//    @JsonIgnore
//    public ProductStatusEnum getProductStatusEnum() {
//        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
//    }
}
