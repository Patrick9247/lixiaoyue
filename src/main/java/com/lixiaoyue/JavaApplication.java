package com.lixiaoyue;

import com.tangzc.mpe.autotable.EnableAutoTable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoTable
@MapperScan("com.lixiaoyue.mapper")  // 扫描Mapper接口（MyBatis-Plus）
@ComponentScan({"com.lixiaoyue.*"})
public class JavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
        System.out.println("项目启动成功！访问地址：http://localhost:8080/api");
        System.out.println("项目启动成功！访问接口地址：http://localhost:8080/api/doc.html");
    }
}