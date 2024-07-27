package com.yupi.yurpc.fault.retry;

public interface RetryStrategyKeys {
    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间重试
     */
    String FIXED_INTERVAL = "fixedInterval";
}
