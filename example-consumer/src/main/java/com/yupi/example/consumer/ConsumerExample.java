package com.yupi.example.consumer;

import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.bootstrap.ConsumerBootstrap;
import com.yupi.yurpc.config.RpcConfig;
import com.yupi.yurpc.proxy.ServiceProxyFactory;
import com.yupi.yurpc.utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
        //服务提供者初始化
        ConsumerBootstrap.init();

        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("lixi");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}