package com.git.onedayrex.springhttpinvokeenhance.config;

import com.git.onedayrex.springhttpinvokeenhance.common.annotation.InvokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Component;

/**
 * 处理httpinvoke service
 */
@Component
public class InvokeServicePost implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(InvokeServicePost.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        InvokeService invokeService = bean.getClass().getAnnotation(InvokeService.class);
        if (invokeService == null) {
            return bean;
        }
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(bean);
        httpInvokerServiceExporter.setServiceInterface(invokeService.value());
        log.info("--->regist rpc service[{}]", beanName);
        return httpInvokerServiceExporter;
    }

}
