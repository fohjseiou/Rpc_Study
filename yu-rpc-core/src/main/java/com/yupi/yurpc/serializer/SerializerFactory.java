package com.yupi.yurpc.serializer;

import com.yupi.yurpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化工厂(用于获取序列化对象)  工厂模式加单例模式
 */
public class SerializerFactory {

  /**
   * 使用静态代码块在，在工厂首次加载时候就会调用Spiload的load方法加载序列化接口的所有实现类，之后就可以调用getInstance方法获得实例对象
   */
//  private static final Map<String,Serializer> KEY_SERIALIZER_MAP = new HashMap<>(){{
//    put(SerializerKeys.JDK,new JdkSerializer());
//    put(SerializerKeys.JSON,new JsonSerializer());
//    put(SerializerKeys.KRYO,new KryoSerializer());
//    put(SerializerKeys.HESSIAN,new HessianSerializer());
//  }};
  static {
    SpiLoader.load(Serializer.class);
  }

  /**
   * 默认序列化器
   */
  private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

  /**
   * 获取实例
   */
  public static Serializer getInstance(String key){
     return SpiLoader.getInstance(Serializer.class, key);
  }

}
