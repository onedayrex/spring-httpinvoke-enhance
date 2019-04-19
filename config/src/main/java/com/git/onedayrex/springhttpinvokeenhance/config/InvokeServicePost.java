package com.git.onedayrex.springhttpinvokeenhance.config;

import com.git.onedayrex.springhttpinvokeenhance.annotation.InvokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * 处理httpinvoke service
 */
@Component
public class InvokeServicePost implements ImportBeanDefinitionRegistrar {
    private static final Logger log = LoggerFactory.getLogger(InvokeServicePost.class);


    private static final String ANNOTATION_NAME = "com.git.onedayrex.springhttpinvokeenhance.annotation.EnableInvokeService";


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ANNOTATION_NAME);
        String basePackage = (String) annotationAttributes.get("basePackage");
        ClassPathInvokeScanner scanner = new ClassPathInvokeScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(InvokeService.class));
        Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan(basePackage);
        if (!beanDefinitionHolders.isEmpty()) {
            for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
                log.info("regist invoke service→[{}]", beanDefinitionHolder.getBeanName());
                registry.registerBeanDefinition(beanDefinitionHolder.getBeanName(), beanDefinitionHolder.getBeanDefinition());
            }
        }
    }
}
