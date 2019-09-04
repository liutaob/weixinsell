package com.jxust.sell.repository;

import com.google.common.collect.Maps;
import com.jxust.sell.entity.ProductInfo;
import com.jxust.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * ProductInfoRepository Dao层接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    /**
     * 插入商品
     */
    @Test
    @Transactional//回滚
    public void saveTest() {
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductId("123459");
//        productInfo.setProductName("狗不理包子");
//        productInfo.setProductPrice(new BigDecimal(3.5));
//        productInfo.setProductStock(150);
//        productInfo.setProductDescription("狗都不理包子");
//        productInfo.setProductIcon("http://xxxxx.jpg");
////        productInfo.setProductStatus(0);
//        productInfo.setCategoryType(10);


        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123461");
        productInfo.setProductName("可乐鸡翅");
        productInfo.setProductPrice(new BigDecimal(19.2));
        productInfo.setProductStock(8878);
        productInfo.setProductDescription("红烧鸡翅膀我最爱吃");
        productInfo.setProductIcon("http://yyyyx.jpg");
//        productInfo.setProductStatus(0); //默认就是0
        productInfo.setCategoryType(3);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    /**
     * 根据商品状态查找商品集合
     *
     * @throws Exception
     */
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = repository.findByProductStatus(1);
        Assert.assertNotEquals(1, productInfoList.size());
    }

    /**
     * 根据商品id批量查找
     *
     * @throws Exception
     */
    @Test
    public void findByProductIdIsIn() throws Exception {
        List<ProductInfo> productInfoList = repository.findByProductIdIn(Arrays.asList("123456", "123458"));
        Assert.assertNotEquals(0, productInfoList.size());
    }

    /**
     * 自定义查询 根据名字查商品
     */
    @Test
    public void findByProductNameWithCustom() {
        ProductInfo product = repository.findByProductNameWithCustom("可乐鸡翅");
        Assert.assertNotNull(product);
    }





    /**
     * 插入商品对象 JQPL本地sql方式 实体不支持
     */
    @Test
    @Transactional
    public void insertProduct() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123462");
        productInfo.setProductName("含笑半步颠");
        productInfo.setProductPrice(new BigDecimal(9999.9));
        productInfo.setProductStock(1);
        productInfo.setProductDescription("居家旅行，杀人必备");
        productInfo.setProductIcon("http://111x.jpg");
//        productInfo.setProductStatus(0); //默认就是0
        productInfo.setCategoryType(10);
        repository.insertProduct(productInfo);
    }

    /**
     * 插入商品对象字符串作参数 JQPL本地sql方式 实体不支持 不推荐
     */
    @Test
    @Transactional
    public void insertProductByString() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123462");
        productInfo.setProductName("含笑半步颠");
        productInfo.setProductPrice(new BigDecimal(9999.9));
        productInfo.setProductStock(1);
        productInfo.setProductDescription("居家旅行，杀人必备");
        productInfo.setProductIcon("http://111x.jpg");
        productInfo.setCategoryType(10);
        repository.insertProductByString(productInfo.getProductId(),productInfo.getProductName(),productInfo.getProductPrice(),productInfo.getProductStock(),
                productInfo.getProductDescription(),productInfo.getProductIcon(),productInfo.getCategoryType());
    }
    /**
     * 插入商品Map作参数 JQPL本地sql方式 实体不支持 不推荐
     */
    @Test
    @Transactional
    public void insertProductByMap() {
        // TODO 测通传Map、写注释调整代码顺序、继续根据订单从商品库存开始
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123462");
        productInfo.setProductName("含笑半步颠");
        productInfo.setProductPrice(new BigDecimal(9999.9));
        productInfo.setProductStock(1);
        productInfo.setProductDescription("居家旅行，杀人必备");
        productInfo.setProductIcon("http://111x.jpg");
        productInfo.setCategoryType(10);

        ConcurrentMap<String, Object> map = Maps.newConcurrentMap();
        map.put("productId",productInfo.getProductId());
        map.put("productName",productInfo.getProductName());
        map.put("productPrice",productInfo.getProductPrice());
        map.put("productStock",productInfo.getProductStock());
        map.put("productDescription",productInfo.getProductDescription());
        map.put("productIcon",productInfo.getProductIcon());
        map.put("categoryType",productInfo.getCategoryType());

        Object o = map.get("productId");
        System.out.println(o instanceof String);
        Object o1 = map.get("productPrice");
        System.out.println(o1 instanceof BigDecimal);
        Object o2 = map.get("categoryType");
        System.out.println(o2 instanceof Integer);

        int i = repository.insertProductByMap(map);
        Assert.assertEquals(1,i);
    }




    /**
     * 根据商品id删除商品
     *
     * @throws Exception
     */
    @Test
    @Transactional//调用删改必须加事务，回滚
    public void deleteProductById() throws Exception {
        int i = repository.deleteProductById("123460");
        Assert.assertEquals(1, i);
    }


    /**
     * 更新商品信息
     */
    @Test
    @javax.transaction.Transactional//两种注解都可
    public void updateProduct() {
        //1，商品对象为参数
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductId("123460");
//        productInfo.setProductName("可乐鸡翅");
//        productInfo.setProductPrice(new BigDecimal(19.2));
//        productInfo.setProductStock(8878);
//        productInfo.setProductDescription("红烧鸡翅膀我最爱吃");
//        productInfo.setProductIcon("http://yyyyx.jpg");
//        productInfo.setProductStatus(0); //默认就是0
//        productInfo.setCategoryType(3);
//        int i = repository.updateProduct(productInfo);//更改返回1，商品不存在未更改返回0
//        Assert.assertEquals(1, i);

        //2，先根据id查
        ProductInfo info = repository.findById("123460").orElse(null);
        Assert.assertNotNull(info);
        info.setProductName("可乐鸡翅升级版");
        info.setProductPrice(new BigDecimal(39.2));
        info.setProductStock(88);
        info.setProductDescription("红烧鸡翅膀我最爱吃");
        info.setProductIcon("http://yyyyx.jpg");
        info.setProductStatus(1); //默认就是0
        info.setCategoryType(10);
        int i = repository.updateProduct(info);
        Assert.assertEquals(1, i);
    }

    /**
     * 根据商品id更新商品状态
     */
    @Test
    @javax.transaction.Transactional//两种注解都可
    public void updateProductStatus() {
        //将123460号商品下架
        int i = repository.updateProductStatus(ProductStatusEnum.DOWN.getCode(), "123460");
        Assert.assertNotEquals(0, i);
    }
}