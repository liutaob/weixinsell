package com.jxust.sell.repository;

import com.jxust.sell.entity.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ProductCategoryRepository Dao层接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    //查询所有商品类目
    @Test
    public void findListTest() {
        List<ProductCategory> list = repository.findAll();
        list.forEach(productCategory -> System.out.println(productCategory));
        list.forEach(System.out::println);
    }

    //插入商品类目
    @Test
//    @Transactional//测试中完全回滚
    @org.springframework.transaction.annotation.Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("男生最爱", 5);
        ProductCategory result = repository.save(productCategory);
        //断言测试是否通过
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }

    //根据商品类型批量查找商品类目
    @Test
    public void findByCategoryTypeIn() {
        ArrayList<Integer> list = Lists.newArrayList(2, 3, 4);
        List<Integer> list1 = Arrays.asList(2, 10);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list1);
        Assert.assertNotNull(result);
    }

    //根据类目Id测试findOne、findById
    @Test
    public void findByCategoryId() {
        Optional<ProductCategory> optional = repository.findById(1);
        ProductCategory productCategory3 = optional.orElse(null);
        ProductCategory productCategory = optional.get();

        //使用findById替代findOne，不存在会报NoSuchElementExcpetion
        Optional<ProductCategory> optional2 = repository.findById(99);
        ProductCategory productCategory1 = optional2.orElse(null);
        ProductCategory productCategory2 = productCategory1 != null ? optional2.get() : null;

        //findOne方式，结合匹配器 类比逆向工程
        //参考https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/ 5.6.4
        //ExampleMatcher matcher = ExampleMatcher.matching()
        //  .withMatcher("firstname", match -> match.endsWith())
        //  .withMatcher("firstname", match -> match.startsWith());
        //}

        ProductCategory category = new ProductCategory();
        List<ProductCategory> all1 = repository.findAll();
        List<ProductCategory> all2 = repository.findAll(Example.of(category));
        category.setCategoryId(1);
        Example<ProductCategory> example = Example.of(category);
        Optional<ProductCategory> one = repository.findOne(example);
        ProductCategory category1 = one.get();
    }
}