package com.yupi.yurpc.springboot.starter.bootstrap;


import com.yupi.yurpc.proxy.ServiceProxyFactory;
import com.yupi.yurpc.springboot.starter.annotation.RpcReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * Rpc服务消费者启动
 */
@Slf4j
public class RpcConsumerBootstrap implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        //遍历对象所有的属性
        Field[] declaredFields = beanClass.getDeclaredFields(); //获取类中所有的属性(public、protected、default、private),但不包含继承的属性
        for (Field field : declaredFields) {
            RpcReference rpcReference = field.getAnnotation(RpcReference.class);
            if (rpcReference != null){
                //为属性生成代理对象
                Class<?> interfaceClass = rpcReference.interfaceClass();
                if (interfaceClass == void.class){
                    interfaceClass = field.getType(); //返回这个变量的类型
                }
                field.setAccessible(true);//设置允许通过反射访问私有成员变量
                Object proxyObject = ServiceProxyFactory.getProxy(interfaceClass);
                try {
                  field.set(bean,proxyObject);
                  field.setAccessible(false);//设置不允许通过反射访问是私有变量
                }catch (IllegalAccessException e){
                    return new RuntimeException("为字段注入代理对象失败",e);
                }
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
