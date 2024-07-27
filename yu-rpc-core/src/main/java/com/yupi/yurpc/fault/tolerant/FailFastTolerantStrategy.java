package com.yupi.yurpc.fault.tolerant;

import com.yupi.yurpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败 - 容错策略（立即通知外层调用方）
 */
public class FailFastTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String, Object> content, Exception e) {
        throw new RuntimeException("服务报错",e);
    }
}
