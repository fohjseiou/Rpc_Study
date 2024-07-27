package com.yupi.yurpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * mock服务代理
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke{}",method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成对应类型的默认返回值
     * @param type
     * @return
     */
    private Object getDefaultObject(Class<?> type) {
        //基本类型
        if (type.isPrimitive()){
            if (type == boolean.class){
                return false;
            }else if(type == short.class){
                return (short)0;
            }else if(type == int.class){
                return 0;
            }else if(type == long.class){
                return 0L;
            }
        }
       //对象类型
        return null;
    }
}
