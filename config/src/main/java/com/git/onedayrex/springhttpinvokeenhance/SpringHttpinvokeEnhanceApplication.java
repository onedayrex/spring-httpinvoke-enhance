package com.git.onedayrex.springhttpinvokeenhance;

import com.git.onedayrex.springhttpinvokeenhance.annotation.EnableInvokeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableInvokeService(basePackage ="com.git.onedayrex")
public class SpringHttpinvokeEnhanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHttpinvokeEnhanceApplication.class, args);
    }
}
