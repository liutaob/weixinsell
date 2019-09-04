/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.controller.buyer;

import com.google.common.collect.Lists;
import com.jxust.sell.entity.ProductCategory;
import com.jxust.sell.entity.ProductInfo;
import com.jxust.sell.service.CategoryService;
import com.jxust.sell.service.ProductService;
import com.jxust.sell.util.ResultVOUtil;
import com.jxust.sell.vo.ProductInfoVO;
import com.jxust.sell.vo.ProductVO;
import com.jxust.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 买家商品Controller
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product") // 缓存,product:list
    public ResultVO list() {
        // 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 精简方法(java8，一次性查询商品类目,map映射中仅仅提取商品的类目)  避免在循环中每次都查商品数据库
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        // 根据商品类目id,查询商品类目对象
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //开始封装数据，商品视图集合
        List<ProductVO> productVOList = Lists.newLinkedList();
        //先遍历商品类目
        productCategoryList.forEach(productCategory -> {
            //封装商品视图
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            //每个商品类目对应一个集合
            ArrayList<ProductInfoVO> productInfoVOList = Lists.newArrayList();
            productInfoList.forEach(productInfo -> {
                //利用Spring BeanUtils工具类复制属性封装商品详情视图
                ProductInfoVO productInfoVO = new ProductInfoVO();
                //需保持属性名一致，个数不一致缺省属性为默认值
                BeanUtils.copyProperties(productInfo, productInfoVO);
                //如果相等添加进商品详情视图集合
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    productInfoVOList.add(productInfoVO);
                }
            });

            productVO.setProductInfoVOList(productInfoVOList);
            //商品视图封装完成，添加到对应集合
            productVOList.add(productVO);
        });

        //返回结果视图
        return ResultVOUtil.success(productVOList);
    }
}
