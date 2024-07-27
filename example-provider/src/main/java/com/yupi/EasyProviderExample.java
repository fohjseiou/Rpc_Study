package com.yupi;

import com.yupi.example.common.service.UserService;
import com.yupi.example.provider.UserServiceImpl;
import com.yupi.yurpc.RpcApplication;
import com.yupi.yurpc.bootstrap.ProviderBootstrap;
import com.yupi.yurpc.model.ServiceRegisterInfo;
import com.yupi.yurpc.registry.LocalRegistry;
import com.yupi.yurpc.service.HttpServer;
import com.yupi.yurpc.service.VertxHttpServer;
import com.yupi.yurpc.service.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * 简易服务提供者实例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //RPC 框架初始化
//        RpcApplication.init();
//
//        //注册服务
//        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
//
//        //启动 web服务
//        HttpServer httpServer = new VertxHttpServer();
//        httpServer.doStart(8080);

        //调用我们封装好的方法
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new
                ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        //服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);


    }
}