package com.yupi.examplespringbootprovider.provider;

import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService
public class UserServiceImpl implements UserService {
    public User getUser(User user){
        System.out.println(user.getName());
        return user;
    }
}
