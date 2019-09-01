package com.jxust.sell.repository;

import com.jxust.sell.entity.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ProductCategoryRepository Dao层接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findListTest() {
        List<ProductCategory> list = repository.findAll();
        list.forEach(productCategory -> System.out.println(productCategory));
        list.forEach(System.out::println);
    }

    @Test
    @Transactional//测试中完全回滚
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("男生最爱", 4);
        ProductCategory result = repository.save(productCategory);
        //断言测试是否通过
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeIn(){
        ArrayList<Integer> list = Lists.newArrayList(2, 3, 4);
        List<Integer> list1 = Arrays.asList(2, 5);
        List<ProductCategory> result = repository.findByCategoryTypeIsIn(list1);
        Assert.assertNotNull(result);
    }
}