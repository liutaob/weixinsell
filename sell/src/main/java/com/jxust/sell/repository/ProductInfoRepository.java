/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.repository;

import com.jxust.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品详情dao
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    //根据id批量查询
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

    //根据商品名自定义查询
    //    @Query("SELECT e FROM #{#entityName} e WHERE e.productName=:productName")
//    @Query(value = "SELECT * FROM product_info p WHERE p.product_name=?1",nativeQuery = true)
//    @Query(value = "SELECT * FROM product_info p WHERE p.product_name=?#{[0]}",nativeQuery = true)
    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.productName=?#{[0]}")
    ProductInfo findByProductNameWithCustom(@Param("productName") String productName);


    //参数为对象的插入商品    注意:JPQL只支持本地sql Insert操作，实体不支持
//    @Query(value = "insert into product_info(" +
//            "product_id,product_name,product_price,product_stock,product_description,product_icon,category_type" +
//            ") VALUES(:#{#productInfo.productId}," +
//            ":#{#productInfo.productName}," +
//            ":#{#productInfo.productPrice}," +
//            ":#{#productInfo.productStock}" +
//            ",:#{#productInfo.productDescription}," +
//            ":#{#productInfo.productIcon}," +
//            ":#{#productInfo.categoryType})",nativeQuery = true)
//    @Modifying
//    void insertProduct(@Param("productInfo") ProductInfo productInfo);

    //参数为对象的插入商品    注意:JPQL只支持本地sql Insert操作，实体不支持  起别名 无提示不推荐
    @Query(value = "insert into product_info(" +
            "product_id,product_name,product_price,product_stock,product_description,product_icon,category_type" +
            ") VALUES(" +
            ":#{#proInfo.productId}," +
            ":#{#proInfo.productName}," +
            ":#{#proInfo.productPrice}," +
            ":#{#proInfo.productStock}," +
            ":#{#proInfo.productDescription}," +
            ":#{#proInfo.productIcon}," +
            ":#{#proInfo.categoryType})", nativeQuery = true)
    @Modifying
    void insertProduct(@Param("proInfo") ProductInfo productInfo);

    //参数为对象的插入商品    注意:JPQL只支持本地sql Insert操作，实体不支持  不封装参数插入 不推荐
    @Query(value = "insert into product_info(" +
            "product_id,product_name,product_price,product_stock,product_description,product_icon,category_type" +
            ") VALUES(" +
            "?#{[0]}," +
            "?#{[1]}," +
            "?#{[2]}," +
            "?#{[3]}," +
            "?#{[4]}," +
            "?#{[5]}," +
            "?#{[6]})", nativeQuery = true)
    @Modifying
    void insertProductByString(String productId, String productName, BigDecimal productPrice, Integer productStock,
                               String productDescription, String productIcon, Integer categoryType);

    //参数为对象的插入商品    注意:JPQL只支持本地sql Insert操作，实体不支持  不封装参数插入 不推荐
    @Query(value = "insert into product_info(" +
            "product_id,product_name,product_price,product_stock,product_description,product_icon,category_type" +
            ") VALUES(" +
            ":{productId}," +
            ":{productName}," +
            ":{productPrice}," +
            ":{productStock}," +
            ":{productDescription}," +
            ":{productIcon}," +
            ":{categoryType}", nativeQuery = true)
//    @Query(value = "insert into product_info(" +
//            "product_id,product_name,product_price,product_stock,product_description,product_icon,category_type" +
//            ") VALUES(" +
//            ":#{#maputil.arg2Type(:#map.get('productId'))}," +
//            ":#{#maputil.arg2Type(:#map.get('productName'))}," +
//            ":#{#maputil.arg2Type(:#map.get('productPrice'))}," +
//            ":#{#maputil.arg2Type(:#map.get('productStock'))}," +
//            ":#{#maputil.arg2Type(:#map.get('productDescription'))}," +
//            ":#{#maputil.arg2Type(:#map.get('productIcon'))}," +
//            ":#{#maputil.arg2Type(:#map.get('categoryType'))}", nativeQuery = true)
    @Modifying
    int insertProductByMap(Map map);


    //使用JPQL的本地sql自定插入
//    @Query(value = "DELETE FROM product_info WHERE product_id = ?1", nativeQuery = true)
    @Query(value = "DELETE FROM ProductInfo  p WHERE productId = ?1")
    @Modifying
//删改需要加上该注解
    //需要在service内加事务，repository默认只有只读事务
    int deleteProductById(String productId);


    //更新商品
//    @Query(value = "UPDATE product_info p SET " +
//            "p.product_name=:#{#productInfo.productName}," +
//            "p.product_price=:#{#productInfo.productPrice}," +
//            "p.product_stock=:#{#productInfo.productStock}," +
//            "p.product_description=:#{#productInfo.productDescription}," +
//            "p.category_type=:#{#productInfo.categoryType}," +
//            "p.product_status=:#{#productInfo.productStatus}," +
//            "p.product_icon=:#{#productInfo.productIcon} " +
//            "WHERE p.product_id=:#{#productInfo.productId}", nativeQuery = true)
    @Query(value = "UPDATE ProductInfo p SET " +
            "p.productName=:#{#productInfo.productName}," +
            "p.productPrice=:#{#productInfo.productPrice}," +
            "p.productStock=:#{#productInfo.productStock}," +
            "p.productDescription=:#{#productInfo.productDescription}," +
            "p.categoryType=:#{#productInfo.categoryType}," +
            "p.productStatus=:#{#productInfo.productStatus}," +
            "p.productIcon=:#{#productInfo.productIcon} " +
            "WHERE p.productId=:#{#productInfo.productId} ")
    @Modifying
    int updateProduct(@Param("productInfo") ProductInfo productInfo);

    //更新商品状态
    @Query(value = "UPDATE ProductInfo p SET p.productStatus= :productStatus WHERE p.productId=:productd") //结合@Param
//    @Query(value = "UPDATE ProductInfo p SET p.productStatus= ?1 WHERE p.productId=?2")
//    @Query(value = "UPDATE ProductInfo p SET p.productStatus= ?#{[0]} WHERE p.productId=?#{[1]}") //骨架检测报红忽略
    @Modifying
//返回值只支持int或void  @Param注解类比@PathVariable,同名可忽略，不同需要起别名
    int updateProductStatus(@Param("productStatus") Integer productStatus, @Param("productd") String productId);

}
