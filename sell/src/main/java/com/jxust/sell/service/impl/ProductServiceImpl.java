/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.service.impl;

import com.jxust.sell.dto.CartDTO;
import com.jxust.sell.entity.ProductInfo;
import com.jxust.sell.enums.ProductStatusEnum;
import com.jxust.sell.enums.ResultEnum;
import com.jxust.sell.exception.SellException;
import com.jxust.sell.repository.ProductInfoRepository;
import com.jxust.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 使用CachePut更新，必须返回值必须一样，key不可省略，省略则使用默认的方法参数作为key,导致key不同
    // @Cacheable(cacheNames = "product", key = "#productId")
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // @CachePut(cacheNames = "product", key = "#productInfo.getProductId()")
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    // 加库存
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        List<String> productIdList = cartDTOList.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        List<ProductInfo> productInfoList = repository.findByProductIdIn(productIdList);

        for (CartDTO cartDTO : cartDTOList) {
//            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            ProductInfo productInfo = repository.findOne(Example.of(new ProductInfo().setProductId(cartDTO.getProductId()))).orElse(null);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    public ProductInfo onSale(String productId) {
        return null;
    }

    @Override
    public ProductInfo offSale(String productId) {
        return null;
    }

//    // 减库存
//    @Override
//    @Transactional
//    public void decreaseStock(List<CartDTO> cartDTOList) {
//        for (CartDTO cartDTO : cartDTOList) {
//            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
//            if (productInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//
//            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
//            if (result < 0) {
//                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
//            }
//
//            productInfo.setProductStock(result);
//
//            repository.save(productInfo);
//        }
//    }
//
//    // 上架商品
//    @Override
//    public ProductInfo onSale(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if (productInfo == null) {
//            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
//            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//
//        // 更新
//        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
//        return repository.save(productInfo);
//    }
//
//    // 下架商品
//    @Override
//    public ProductInfo offSale(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if (productInfo == null) {
//            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
//            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//
//        // 更新
//        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
//        return repository.save(productInfo);
//    }
}
