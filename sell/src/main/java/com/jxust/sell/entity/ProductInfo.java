/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jxust.sell.enums.ProductStatusEnum;
import com.jxust.sell.util.EnumUtil;
import lombok.Builder;
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
//@NoArgsConstructor
public class ProductInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1441872166231127730L;

    /*Integer有上限，采用字符串*/
    @Id
    private String productId;

    /**
     * 名字.
     */
    private String productName;

    /**
     * 单价.
     */
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    private Integer productStock;

    /**
     * 描述.
     */
    private String productDescription;

    /**
     * 小图.
     */
    private String productIcon;

    /**
     * 状态, 0(正常),1(下架)，默认是上架状态.
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 类目编号.
     */
    private Integer categoryType;

    /**
     * 创建时间.
     */
//    private Date createTime;
//

    /**
     * 更新时间.
     */
//    private Date updateTime;
    @Builder
    public ProductInfo() {
    }


//    @Builder(toBuilder = true) //链式创建对象 避免和空参冲突
//    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productStock = productStock;
//        this.productDescription = productDescription;
//        this.productIcon = productIcon;
//        this.productStatus = productStatus;
//        this.categoryType = categoryType;
//    }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
