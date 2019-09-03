/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.repository;

import com.jxust.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品详情dao
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    //根据id批量查询
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

//    //根据商品批量插入
//    @Query(value = "insert into product_info(authority,group_id) VALUES(?1,?2)", nativeQuery = true)
//    @Modifying//增删改需要加上该注解
//    @Transactional//事务
//    void insertProductList(List<ProductInfo> list);

    //使用JPQL自定插入，修改删除同理 增删改查都写一个
    @Query(value = "insert into product_info" +
            "(product_id,product_name,product_price,product_stock,product_descripition,product_icon,product_status,category_type) " +
            "VALUES(:#{#productInfo.productId},:#{#productInfo.productName}," +
            ":#{#productInfo.productPrice},:#{#productInfo.productStock}," +
            ":#{#productInfo.productDescription},:#{#productInfo.productIcon},:#{#productInfo.productStatus}," +
            ":#{#productInfo.categoryType})", nativeQuery = true)
    @Modifying//增删改需要加上该注解
    @Transactional//事务
    ProductInfo insertOne(ProductInfo productInfo);
}
