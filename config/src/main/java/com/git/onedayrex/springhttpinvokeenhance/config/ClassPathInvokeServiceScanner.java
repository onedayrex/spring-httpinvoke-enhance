package com.git.onedayrex.springhttpinvokeenhance.config;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassPathInvokeServiceScanner extends ClassPathBeanDefinitionScanner {

    public ClassPathInvokeServiceScanner(BeanDefinitionRegistry registry) {
        super(registry,false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        Set<BeanDefinitionHolder> realHolders = new HashSet<>();
        if (beanDefinitionHolders != null && !beanDefinitionHolders.isEmpty()) {
            GenericBeanDefinition definition;
            for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
                definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
                //获取接口名称
                String className = definition.getBeanClassName();
                Class<?> beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    logger.error("class not fount", e);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                Assert.notEmpty(interfaces,"invoke service not a interfaces");
                Class<?> anInterface = interfaces[0];
                //设置bean远程调用bean信息
                List<PropertyValue> propertyValueList = new ArrayList<>();
                propertyValueList.add(new PropertyValue("service", beanClass));
                propertyValueList.add(new PropertyValue("serviceInterface", anInterface));
                String beanName = "/invokeRemote/" + anInterface.getSimpleName();
                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
                beanDefinition.setBeanClass(HttpInvokerServiceExporter.class);
                beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                beanDefinition.setPropertyValues(new MutablePropertyValues(propertyValueList));
                BeanDefinitionHolder realholder = new BeanDefinitionHolder(definition, beanName);
                super.getRegistry().removeBeanDefinition(beanDefinitionHolder.getBeanName());
                realHolders.add(realholder);
            }
        }
        return realHolders;
    }
}
