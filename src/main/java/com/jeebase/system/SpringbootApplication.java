package com.jeebase.system;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: SpringbootApplication
 * @Description: 指定项目为springboot，由此类当作程序入口，自动装配 web 依赖的环境
 * @author fyy
 * @date  
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@EnableTransactionManagement
@MapperScan("com.jeebase.*.*.mapper")
@ComponentScan(basePackages = "com.jeebase.*.*.service")
@ComponentScan(basePackages = "com.jeebase.*.*.aspect")
@ComponentScan(basePackages = "com.jeebase.*.*.component")
@ComponentScan(basePackages = "com.jeebase.*.*.controller")
@EnableCaching
@EnableScheduling
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
