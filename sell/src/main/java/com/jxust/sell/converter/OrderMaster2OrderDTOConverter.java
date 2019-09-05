/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.converter;

import com.jxust.sell.dto.OrderDTO;
import com.jxust.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 将订单转换为订单数据传输对象DTO
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public class OrderMaster2OrderDTOConverter {

    /**
     * 单个对象转化 .
     */
    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 列表对象转化 .
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        // lambda
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
