package com.git.onedayrex.springhttpinvokeenhance.config;

import com.git.onedayrex.springhttpinvokeenhance.BaseTest;
import org.junit.Test;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import static org.junit.Assert.*;

public class InvokeServicePostTest extends BaseTest {

    @Test
    public void postProcessBeforeInitialization() {

    }

    public static void main(String[] args) {
        int i=0;
        i=++i;
        System.out.println(i);
    }
}