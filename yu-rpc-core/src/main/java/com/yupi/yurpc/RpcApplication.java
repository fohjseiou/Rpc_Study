package com.yupi.yurpc;

import com.yupi.yurpc.config.RegistryConfig;
import com.yupi.yurpc.config.RpcConfig;
import com.yupi.yurpc.constant.RpcConstant;
import com.yupi.yurpc.registry.Registry;
import com.yupi.yurpc.registry.RegistryFactory;
import com.yupi.yurpc.spi.SpiLoader;
import com.yupi.yurpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * PRC框架应用
 * 相当于holder，存放了项目全局用到的变量。双检锁单例模式的实现
 */
@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig; //volatile

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init,config = {}", newRpcConfig.toString());

        //注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        //SpiLoader.getInstance(Registry.class,registryConfig.getRegistry());  你这么写你工厂存在意义是干嘛的，他不就是简化你的开发嘛，而且不用工厂，你的静态代码块怎么初始化呢
        //调用工厂模式，获取Registry实例
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        //将注册配置类中的信息预加载
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        //创建并注册Shutdown Hook,JVM退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            //配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
