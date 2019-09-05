/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.service;

import com.jxust.sell.dto.OrderDTO;

/**
 * 买家service
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public interface BuyerService {

    /** 查询一个订单. */
    OrderDTO findOrderOne(String openid, String orderId);

    /** 取消订单. */
    OrderDTO cancelOrder(String openid, String orderId);
}