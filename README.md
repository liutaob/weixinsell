[TOC]

# Springboot微信点餐系统

- 功能展示

![买](images\p3.png)

![功能展示](images\p4.png)

- 软件版本

![tech](images\p5.png)

## 项目设计

### 技术要点

- 前后端分离![tech](images\p6.png)
- 相关技术![tech](images\p7.png)![tech](images\p8.png)

​                    ![tech](images\p9.png)

### 角色划分

- 买家：微信端
- 卖家：PC端

### 功能模块分析

- 功能分析

![module](images\analy.png)

- 关系图

![p0](images\p0.png)



### 部署架构

![p1](images\p1.png)

### 数据库设计

- 数据库表关系

  ![p2](images\p2.png)

- 数据库表设计

~~~sql
create table `product_inf`(
	`product_id` varchar(32) not null,
	`product_name` varchr(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '单价',
	`product_descripition` varchar(64) comment '描述',
	`product_icon` varchar(512) comment '小图',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key(`product_id`)
) comment '商品表'

create table `product_category`(
	`category_id` int not null auto_increment,
 	`category_name` varchar(64) not null comment '类目名字',
  	`category_type` int not null comment '类目编号',
  	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key(`category_id`)，
  	unique key `uqe_category_type` (`category_type`)
) comment '类目表'


~~~

## 开发环境搭建

## 功能实现