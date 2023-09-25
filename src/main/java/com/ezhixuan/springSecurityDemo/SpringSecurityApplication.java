package com.ezhixuan.springSecurityDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.ezhixuan.springSecurityDemo.mapper")
public class SpringSecurityApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringSecurityApplication.class,args);
    }
}
