package com.yupi.yurpc.registry.retry;

import com.yupi.yurpc.fault.retry.NoRetryStrategy;
import com.yupi.yurpc.fault.retry.RetryStrategy;
import com.yupi.yurpc.model.RpcResponse;
import org.junit.Test;

public class RetryStrategyTest {
    RetryStrategy retryStrategy = new NoRetryStrategy();

    @Test
    public void doRetry(){


        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次，停止重试");
            e.printStackTrace();
        }
    }

}
