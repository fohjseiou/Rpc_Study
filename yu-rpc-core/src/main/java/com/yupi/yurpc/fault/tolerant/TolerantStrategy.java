package com.yupi.yurpc.fault.tolerant;

import com.yupi.yurpc.model.RpcResponse;

import java.util.Map;

/**
 * 容错策略
 */
public interface TolerantStrategy {
    /**
     *
     * @param content Map形式的上下文，可以用于传递数据
     * @param e  接受到的具体异常信息
     * @return
     */
    RpcResponse doTolerant(Map<String,Object> content,Exception e);
}
