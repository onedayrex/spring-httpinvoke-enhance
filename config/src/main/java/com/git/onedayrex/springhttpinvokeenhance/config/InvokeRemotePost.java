package com.git.onedayrex.springhttpinvokeenhance.config;

import com.git.onedayrex.springhttpinvokeenhance.annotation.InvokeRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InvokeRemotePost implements ImportBeanDefinitionRegistrar{
    private static final Logger log = LoggerFactory.getLogger(InvokeRemotePost.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        if (beanDefinitionNames.length != 0) {
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
                String beanClassName = beanDefinition.getBeanClassName();
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(beanClassName);
                } catch (ClassNotFoundException e) {
                    log.error("class not found", e);
                }
                Field[] fields = clazz.getDeclaredFields();
                if (fields.length != 0) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        InvokeRemote annotation = field.getAnnotation(InvokeRemote.class);
                        if (annotation != null) {
                            Class<?> remoteClazz = field.getType();
                            String url = annotation.value() + "/" + remoteClazz.getSimpleName();
                            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
                            genericBeanDefinition.setBeanClass(HttpInvokerProxyFactoryBean.class);
                            List<PropertyValue> propertyValueList = new ArrayList<>();
                            propertyValueList.add(new PropertyValue("serviceInterface", remoteClazz));
                            propertyValueList.add(new PropertyValue("serviceUrl", url));
                            genericBeanDefinition.setPropertyValues(new MutablePropertyValues(propertyValueList));
                            registry.registerBeanDefinition("Invokeremote" + remoteClazz.getSimpleName(), genericBeanDefinition);
                            log.info("regist invoke remote→[{}]",url, remoteClazz.getSimpleName());
                        }
                    }
                }
            }
        }

    }

}
