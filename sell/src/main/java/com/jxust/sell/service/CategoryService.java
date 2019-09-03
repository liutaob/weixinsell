/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.service;

import com.jxust.sell.entity.ProductCategory;

import java.util.List;

/**
 * 类目服务接口
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public interface CategoryService {
    /**
     * 根据类目id，查询类目.
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有商品类目.
     */
    List<ProductCategory> findAll();

    /**
     * 根据类目id批量查询类目.
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 插入一条商品类目.
     */
    ProductCategory save(ProductCategory productCategory);
}
