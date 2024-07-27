package com.yupi.yurpc.fault.tolerant;

import com.yupi.yurpc.model.RpcResponse;

import java.util.Map;

/**
 * 失败自动恢复（通过调用其他方法，可以理解为降级或者熔断）
 */
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> content, Exception e) {
        //具体实现逻辑还未实现
        return null;
    }
}
