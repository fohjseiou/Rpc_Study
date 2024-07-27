package com.yupi.yurpc.loadbalancer;

import com.yupi.yurpc.registry.EtcdRegistry;
import com.yupi.yurpc.registry.Registry;
import com.yupi.yurpc.spi.SpiLoader;

public class LoadBalancerFactory {
    //静态代码块,当前类被加载时候，进行初始化
    static {
        SpiLoader.load(LoadBalancer.class);
    }

    //默认的负载均衡策略
    private static final LoadBalancer DEFAULT_LOADBALANCER = new RoundRobinLoadBalancer();

    //获取实例
    public static LoadBalancer getInstance(String key){
       return SpiLoader.getInstance(LoadBalancer.class,key);
    }
}
