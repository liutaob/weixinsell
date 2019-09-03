package com.jxust.sell.repository;

import com.jxust.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ProductInfoRepository Dao层接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123459");
        productInfo.setProductName("狗不理包子");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setProductStock(150);
        productInfo.setProductDescription("狗都不理包子");
        productInfo.setProductIcon("http://xxxxx.jpg");
//        productInfo.setProductStatus(0);
        productInfo.setCategoryType(10);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = repository.findByProductStatus(1);
        Assert.assertNotEquals(1, productInfoList.size());
    }

    @Test
    public void findByProductIdIsIn() throws Exception {
        List<ProductInfo> productInfoList = repository.findByProductIdIn(Arrays.asList("123456","123458"));
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void insertOne() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123460");
        productInfo.setProductName("可乐鸡翅");
        productInfo.setProductPrice(new BigDecimal(19.2));
        productInfo.setProductStock(8878);
        productInfo.setProductDescription("红烧鸡翅膀我最爱吃");
        productInfo.setProductIcon("http://yyyyx.jpg");
//        productInfo.setProductStatus(0); //默认就是0
        productInfo.setCategoryType(3);
        ProductInfo result = repository.insertOne(productInfo);
        Assert.assertNotNull(result);
    }

}