/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.service.impl;

import com.google.common.collect.Lists;
import com.jxust.sell.dto.CartDTO;
import com.jxust.sell.entity.ProductInfo;
import com.jxust.sell.enums.ProductStatusEnum;
import com.jxust.sell.enums.ResultEnum;
import com.jxust.sell.exception.SellException;
import com.jxust.sell.repository.ProductInfoRepository;
import com.jxust.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品服务实现
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    /**
     * 查询一个商品信息.
     */
    // 使用CachePut更新，必须返回值必须一样，key不可省略，省略则使用默认的方法参数作为key,导致key不同
    // @Cacheable(cacheNames = "product", key = "#productId")
    @Override
    public ProductInfo findOne(String productId) {
//        return repository.findById(productId).orElse(null);
        return repository.findProductInfoByProductId(productId);
    }

    /**
     * 用自定义的JPQL根据商品名查商品，也可根据命名格式使用接口提供的
     */
    @Override
    public ProductInfo findByProductName(String productName) {
        ProductInfo productInfo = repository.findByProductNameWithCustom(productName);
        return productInfo;
    }

    /**
     * 查询所有在架商品列表.
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 查询所有商品列表.
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * 插入单个商品信息.
     */
    // @CachePut(cacheNames = "product", key = "#productInfo.getProductId()")
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        //1,默认方式
        return repository.save(productInfo);

        //2, 用自定义的JPQL插入商品
//        int i = repository.insertProduct(productInfo);
//        return productInfo = i == 1 ? productInfo : null;
    }

    //修改商品状态  上下架
    @Override
    public ProductInfo updateProductStatus(String productId, Integer productStatus) {
        int i = repository.updateProductStatus(productStatus, productId);
        return i == 0 ? null : this.findOne(productId);
    }

    //更新商品信息
    @Override
    @Transactional
    public ProductInfo updateProduct(ProductInfo productInfo) {
        int i = repository.updateProduct(productInfo);
        return i == 0 ? null : productInfo;
    }

    //根据id删除商品
    @Override
    public void deleteProductById(String productId) {
//        repository.deleteById(productId); //默认方式
//        int i = repository.deleteProductById(productId);//JPQL自定义
        ProductInfo product = this.findOne(productId);
        if (product == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
    }

    //根据ids批量删除
    @Override
    public void deleteProductByBatch(String... productIds) throws Exception {
        for (String productId :
                productIds) {
            this.deleteProductById(productId);
        }
    }

    // 加库存
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        //1,批量一次插入
        List<String> productIdList = cartDTOList.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        List<ProductInfo> productInfoList = repository.findByProductIdIn(productIdList);
        if (productIdList.size() == 0)
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        LinkedList<ProductInfo> insertList = Lists.newLinkedList();
        cartDTOList.forEach(cartDTO -> {
            productInfoList.forEach(productInfo -> {
                productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
                insertList.add(productInfo);
            });
        });
        repository.saveAll(insertList);

        //2,逐条插入
//        for (CartDTO cartDTO : cartDTOList) {
////            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
//            ProductInfo productInfo = repository.findOne(Example.of(new ProductInfo().setProductId(cartDTO.getProductId()))).orElse(null);
//            if (productInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
//            productInfo.setProductStock(result);
////            repository.save(productInfo);
//            repository.insertProduct(productInfo);
//        }
    }

    // 减库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = this.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

//            repository.save(productInfo);
            this.save(productInfo);
        }
    }

    // 上架商品
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findProductInfoByProductId(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
//        return repository.save(productInfo); //保存，存在则更新
//        return this.updateProduct(productInfo);
        return this.updateProductStatus(productId, ProductStatusEnum.UP.getCode());
    }

    // 下架商品
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = this.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
