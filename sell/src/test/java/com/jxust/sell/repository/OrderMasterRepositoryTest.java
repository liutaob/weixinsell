package com.jxust.sell.repository;

import com.jxust.sell.entity.OrderMaster;
import com.jxust.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * OrderMasterRepository Dao接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "2441603211";

    //创建订单
    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234569");
        orderMaster.setBuyerName("刘涛");
        orderMaster.setBuyerPhone("18707956960");
        orderMaster.setBuyerAddress("宜春");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(999.99));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    //更新订单状态
    @Test
    public void updateTest() {
        OrderMaster orderMaster = repository.findById("1234569").orElse(null);
        Assert.assertNotNull(orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotEquals((Integer) 0, result.getPayStatus());
    }

    //根据买家微信Openid查找所属订单
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(2, 3);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);

        Assert.assertNotEquals(0, result.getTotalElements());
    }
}