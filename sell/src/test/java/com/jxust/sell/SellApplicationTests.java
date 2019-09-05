package com.jxust.sell;

import com.jxust.sell.service.CategoryService;
import com.jxust.sell.util.KeyUtil;
import com.jxust.sell.util.MapUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellApplicationTests {

    //各种数据源对比性能，druid监控做的好比较全，HikariCP》druid、 bonecp》tomcat.jdbc》dbcp》c3p0》》jboss	Proxool
    //Springboot1.x 是tomcat-jdbc 2.x是HikariCP
    //引入druid-starter   https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
    //对比以往只是jar包的方式
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ApplicationContext context;

    /**
     * 测试从容器中获取bean
     *
     * @throws SQLException
     */
    @Test
    public void contextLoads() throws Exception {
        Object o = context.getBean(CategoryService.class);
        System.out.println(o.getClass().getName());
        Object o2 = context.getBean(MapUtil.class);
        System.out.println(o2.getClass().getName());
        System.out.println(context.getBean("maputil"));
        Object o3 = context.getBean(KeyUtil.class);
    }

    /**
     * 测试数据源
     *
     * @throws SQLException
     */
    @Test
    public void datasoure() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
        System.out.println(dataSource.toString());
    }

    @Test
    public void generateId() {
        System.out.println(UUID.randomUUID());
        System.out.println(BigInteger.ZERO);
    }

}
