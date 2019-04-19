package com.git.onedayrex.springhttpinvokeenhance.annotation;

import com.git.onedayrex.springhttpinvokeenhance.config.InvokeRemotePost;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(InvokeRemotePost.class)
public @interface EnableInvokeRemote {
}
