package com.olaelectric.BoBTheBuilder;

import com.olaelectric.Controller.GetController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = GetController.class)
public class Qa_util {

    public static void main(String[] args) {
        SpringApplication.run(Qa_util.class,args);
    }

}
