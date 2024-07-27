package com.yupi.yurpc.springboot.starter.annotation;

import com.yupi.yurpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.yupi.yurpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.yupi.yurpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用Rpc注解，用于全局标识项目需要引入RPC框架初始化方法
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {
    /**
     * 需要重新启动Server
     *
     */
    boolean needServer() default true;
}
