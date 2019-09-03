/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.repository;

import com.jxust.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单dao
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //JPA提供的分页是基于物理分页limit查询，效率高于内存分页
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
