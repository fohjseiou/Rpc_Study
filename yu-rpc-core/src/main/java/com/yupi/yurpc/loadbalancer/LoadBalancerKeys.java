package com.yupi.yurpc.loadbalancer;

public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";
    /**
     * 随机
     */
    String RANDOM = "random";

    /**
     * hash
     */
    String CONSISTENT_HASH = "consistentHash";
}
