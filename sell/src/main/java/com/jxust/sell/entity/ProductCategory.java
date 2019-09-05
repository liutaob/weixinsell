/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品类目
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Entity
@DynamicUpdate//自动更新
@Data//包含setter、getter、hashcode、equal、toString方法 如只需部分 @Getter @EqualsAndHashCode
@NoArgsConstructor//替代无参构造
@Accessors(chain = true)//setter返回对象 setXX 方法名是set/get+属性名
//@Accessors(flue = true)//setter返回对象xx（xx） getter xx 方法名是属性名
//@Accessors(prefix = "p")//忽略p 如pid，根据id去生成
public class ProductCategory {

    /**
     * 类目id(使用自增主键).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名字.
     */
    private String categoryName;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    /**
     * 创建时间.
     */
//    private Date createTime;

    /**
     * 更新时间(mysql5.7以上支持自动更新).
     */
//    private Date updateTime;

    //Spring底层创建bean
//    public ProductCategory() {
//    }
    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
