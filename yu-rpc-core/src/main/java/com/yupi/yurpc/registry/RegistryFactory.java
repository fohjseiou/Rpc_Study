package com.yupi.yurpc.registry;

import com.yupi.yurpc.serializer.JdkSerializer;
import com.yupi.yurpc.serializer.Serializer;
import com.yupi.yurpc.spi.SpiLoader;

public class RegistryFactory {
    /**
     * 静态代码块，在工厂被加载时候，就会调用load方法，将注册类加载进来，之后就可以进行初始化方法的调用
     */
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认序列化器
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     */
    public static Registry getInstance(String key){
        return SpiLoader.getInstance(Registry.class, key);
    }

}
