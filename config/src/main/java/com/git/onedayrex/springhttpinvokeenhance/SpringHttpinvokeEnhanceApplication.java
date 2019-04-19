package com.git.onedayrex.springhttpinvokeenhance;

import com.git.onedayrex.springhttpinvokeenhance.annotation.EnableInvokeRemote;
import com.git.onedayrex.springhttpinvokeenhance.annotation.EnableInvokeService;
import com.git.onedayrex.springhttpinvokeenhance.annotation.InvokeRemote;
import com.git.onedayrex.springhttpinvokeenhance.service.InvokeTestServic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableInvokeService(basePackage ="com.git.onedayrex")
@EnableInvokeRemote()
public class SpringHttpinvokeEnhanceApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringHttpinvokeEnhanceApplication.class, args);
    }
}
