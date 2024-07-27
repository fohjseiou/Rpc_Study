package com.yupi.examplespringbootconsumer;

import com.yupi.examplespringbootconsumer.consumer.ExampleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExampleSpringbootConsumer {
    @Resource
    private ExampleServiceImpl exampleService;

    @Test
    void contextLoads() {
      exampleService.test();
    }

}
