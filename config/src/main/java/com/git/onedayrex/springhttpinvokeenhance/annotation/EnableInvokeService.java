package com.git.onedayrex.springhttpinvokeenhance.annotation;

import com.git.onedayrex.springhttpinvokeenhance.config.InvokeServicePost;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(InvokeServicePost.class)
public @interface EnableInvokeService {

    String basePackage();
}
