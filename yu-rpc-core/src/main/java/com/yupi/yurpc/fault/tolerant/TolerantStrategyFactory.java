package com.yupi.yurpc.fault.tolerant;

import com.yupi.yurpc.spi.SpiLoader;

public class TolerantStrategyFactory {
    //静态代码块，在该类被加载时候进行初始化
    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认熔断机制
     */
    private static final TolerantStrategy DEFAULT_RETRY_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
