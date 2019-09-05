package com.jxust.sell.repository;

import com.jxust.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDetailRepository Dao接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    //订单项插入
    @Test
    public void saveTest() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567812");
        orderDetail.setOrderId("1234569");
        orderDetail.setProductIcon("http://xxi.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(12);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    //根据订单id查找订单项
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = repository.findByOrderId("1234569");
        Assert.assertNotEquals(0, orderDetailList.size());
    }

}