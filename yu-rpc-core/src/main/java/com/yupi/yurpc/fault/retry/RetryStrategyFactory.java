package com.yupi.yurpc.fault.retry;

import com.yupi.yurpc.spi.SpiLoader;

/**
 * 重试策略工厂(用于获取重试器对象)
 */
public class RetryStrategyFactory {
  /**
   * 静态代码块，当前类被调用时候进行初始化
   */
  static {
    SpiLoader.load(RetryStrategy.class);
  }

  /**
   * 默认重试器
   */
   private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

  /**
   * 获取加载实例
   */
   public static RetryStrategy getInstance(String key){
     return SpiLoader.getInstance(RetryStrategy.class,key);
   }
}
