package com.jxust.sell.service.impl;

import com.jxust.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

/**
 * 商品类目服务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;//也可用接口
    @PersistenceContext
//    @Autowired
    private EntityManager entityManager;//管理器直接操作 详见JPA

    //单个类目查找
    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    //查询全部类目
    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    //根据商品类型批量查找商品类目
    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    //插入商品类目
    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("兵器", 11);
//        productCategory.setCategoryId(12);    //有id更新 无id插入 区别于JPA管理器不能有id
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }

    //管理器方式插入商品类目
    @Test
    @Transactional
    public void saveByEntityManager() throws Exception {
        ProductCategory productCategory = new ProductCategory("衣服", 13);
//        productCategory.setCategoryId(12);    //有id更新 无id插入 区别于JPA管理器不能有id
        entityManager.persist(productCategory);
    }


}