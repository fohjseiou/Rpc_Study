package com.yupi.yurpc.config;

import com.yupi.yurpc.fault.retry.NoRetryStrategy;
import com.yupi.yurpc.fault.retry.RetryStrategy;
import com.yupi.yurpc.fault.retry.RetryStrategyKeys;
import com.yupi.yurpc.fault.tolerant.TolerantStrategyKeys;
import com.yupi.yurpc.loadbalancer.LoadBalancerKeys;
import com.yupi.yurpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * RPC配置框架
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
   private String name = "yu-rpc";
    /**
     * 版本号
     */
   private String version = "1.0";
    /**
     * 服务器主机名
     */
   private String serverHost = "localhost";
    /**
     * 服务器端口号
     */
   private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;
    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
    /**
     * 重试策略
     */
    private String retryStrategy = RetryStrategyKeys.NO;
    /**
     * 容错策略
     */
    private String tolerant = TolerantStrategyKeys.FAIL_FAST;
}
