package com.yupi.yurpc.config;

import com.yupi.yurpc.registry.RegistryKeys;
import com.yupi.yurpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * RPC 框架注册中心配置
 * 这个类主要是我们可以将配置文件变为对象之后可以可以将配置文件中的属性值，初始化给这个对象
 */
@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = RegistryKeys.ETCD;
    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private Integer password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;
}
