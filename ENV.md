# 环境说明文档
虚拟机工具可选VirtualBox、VMware Workstation Pro或使用云服务器如aliyun、tencent

虚拟机系统centos7

项目构建工具maven

docker容器

[^备注]: 下载速度慢，请配置阿里的docker、maven镜像库

#### 包括软件

* jdk 1.8.0_111
* nginx 1.11.7
* mysql 5.7.17
* redis 3.2.8

##### jdk
* 路径 /usr/local/jdk1.8.0_111

##### nginx
* 路径 /usr/local/nginx
* 启动 nginx
* 重启 nginx -s reload

##### mysql
* 配置 /etc/my.conf
* 账号 root
* 密码 123456
* 端口 3306
* 启动 systemctl start mysqld
* 停止 systemctl stop mysqld

##### redis
* 路径 /usr/local/redis
* 配置 /etc/reis.conf
* 端口 6379
* 密码 123456
* 启动 systemctl start redis
* 停止 systemctl stop redis

##### tomcat
* 路径 /usr/local/tomcat
* 启动 systemctl start tomcat
* 停止 systemctl stop tomcat


#### 安装方式

- 安装版

  [jdk官网](oracle.com)

  [redis官网](redis.io)

  [mysql官网](https://www.mysql.com)

  [tomcat官网](https://tomcat.apache.org/)

  [nginx官网](nginx.org)

- docker版

  [docker安装](http://docs.docker.com/)

  [docker仓库](https://hub.docker.com)

  ~~~docker
  docker run --name redis-test -p 6379:6379 -d --restart=always redis:latest redis-server --appendonly yes --requirepass "123456"

  docker run -p 3306:3306 --name mysqltest --restart always -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7

  docker run --restart always --name mynginx -p 80:80 -d nginx

  docker run -d --name mytomcat -p 8080:8080 --restart tomcat
  ~~~