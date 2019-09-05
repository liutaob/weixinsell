package com.jxust.sell.service.impl;

import com.jxust.sell.entity.ProductInfo;
import com.jxust.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品服务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    //查询一个商品信息
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    /**
     * 用自定义的JPQL根据商品名查商品，也可根据命名格式使用接口提供的
     */
    @Test
    public void findByProductName() throws Exception {
        ProductInfo productInfo = productService.findByProductName("可乐鸡翅");
        Assert.assertNotNull(productInfo);
    }

    /**
     * 查询所有在架商品列表.
     */
    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    /**
     * 查询所有商品列表.
     */
    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    //插入商品
    @Test
    @Transactional//调用默认方式不需要事务（只起回滚），JPQL方式需要事务
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123466");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    //更新商品状态
    @Test
    @javax.transaction.Transactional
    public void updateProductStatus() {
        ProductInfo productInfo = productService.updateProductStatus("123456", ProductStatusEnum.DOWN.getCode());
        Assert.assertNotNull(productInfo);
    }

    //更新商品
    @Test
//    @javax.transaction.Transactional
    public void updateProduct() {
        //1，商品对象为参数
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductId("123460");
//        productInfo.setProductName("可乐鸡翅");
//        productInfo.setProductPrice(new BigDecimal(19.2));
//        productInfo.setProductStock(8878);
//        productInfo.setProductDescription("红烧鸡翅膀我最爱吃");
//        productInfo.setProductIcon("http://yyyyx.jpg");
//        productInfo.setProductStatus(ProductStatusEnum.UP.getCode()); //默认就是0
//        productInfo.setCategoryType(3);
//        ProductInfo productInfo1 = productService.updateProduct(productInfo);//更改返回1，商品不存在未更改返回0
//        Assert.assertNotNull(productInfo1);

        //2，先根据id查
        ProductInfo info = productService.findOne("123460");
        Assert.assertNotNull(info);
        info.setProductName("可乐鸡翅升级版");
        info.setProductPrice(new BigDecimal(39.2));
        info.setProductStock(88);
        info.setProductDescription("红烧鸡翅膀我最爱吃");
        info.setProductIcon("http://yyyyx.jpg");
        //TODO 传参为枚举需要限定，非要传其他数字报异常处理 默认选项前台选择传递
        info.setProductStatus(ProductStatusEnum.UP.getCode()); //默认就是0
        info.setCategoryType(10);
        ProductInfo productInfo = productService.updateProduct(info);
        Assert.assertNotNull(productInfo);
    }

    //单条删除
    @Test
    @Transactional
    public void deleteProductById() throws Exception {
        productService.deleteProductById("123456");
    }

    //批量删除
    @Test
    @Transactional
    public void deleteProductByBatch() throws Exception {
        productService.deleteProductByBatch("123456", "1234567");
    }

    //上架
    @Test
    @Transactional//使用JPQL更新加事务，默认save更新不加事务
    public void onSale() {
        ProductInfo result = productService.onSale("123457");
        Assert.assertEquals(ProductStatusEnum.UP, result.getProductStatusEnum());
    }

    //下架
    @Test
    public void offSale() {
        ProductInfo result = productService.offSale("123457");
        Assert.assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
    }

}