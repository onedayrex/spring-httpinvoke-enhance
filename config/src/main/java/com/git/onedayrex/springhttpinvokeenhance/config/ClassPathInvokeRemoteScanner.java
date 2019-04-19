package com.git.onedayrex.springhttpinvokeenhance.config;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

public class ClassPathInvokeRemoteScanner extends ClassPathBeanDefinitionScanner {
    public ClassPathInvokeRemoteScanner(BeanDefinitionRegistry registry) {
        super(registry,true);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        return beanDefinitionHolders;
    }
}
