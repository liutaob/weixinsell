/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.service;

import com.jxust.sell.dto.CartDTO;
import com.jxust.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品服务接口
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public interface ProductService {

    /** 查询一个商品信息. */
    ProductInfo findOne(String productId);

    /** 查询所有在架商品列表. */
    List<ProductInfo> findUpAll();

    /** 查询所有商品列表. */
    Page<ProductInfo> findAll(Pageable pageable);

    /** 插入单个商品信息. */
    ProductInfo save(ProductInfo productInfo);

    /** 增加库存. */
    void increaseStock(List<CartDTO> cartDTOList);

    /** 减库存. */
    void decreaseStock(List<CartDTO> cartDTOList);

    /** 上架商品. */
    ProductInfo onSale(String productId);

    /** 下架商品. */
    ProductInfo offSale(String productId);
}
