package com.mao.test;

//import com.mao.test.file.File_Listener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @Author Mr mao
 * @Date 14:06
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
@MapperScan({"com.mao.test.dao"})
@ComponentScan("com.mao")
public class MySpringbootApplication {
    public static void main(String[] args) {

        //SpringApplication.run(MySpringbootApplication.class, args);
        SpringApplication.run(MySpringbootApplication.class, args);
    }

}
