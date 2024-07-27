package com.yupi.yurpc.loadbalancer;

import com.yupi.yurpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 一致性hash负载均衡器
 */
public class ConsistentHashLoadBalancer implements LoadBalancer{

    //一致性Hash环，存放虚拟节点
    private final TreeMap<Integer,ServiceMetaInfo> virtualNodes = new TreeMap<>();
    private final  int MAX_COUNT = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()){
            return null;
        }
        //构建hash环表
        for (int i = 0; i < MAX_COUNT; i++) {
            for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#"+i);
                virtualNodes.put(hash,serviceMetaInfo);
            }
        }
        //获取调用请求的hash值
        int hash = getHash(requestParams);
        //在treemap中这是从当前key中选择一个大于或等于当前key的最小条目，如果没有就返回null
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if (entry == null){
            //如果没有大于等于调用请求的hash值的虚拟节点，则返回环首部的节点
            entry = virtualNodes.firstEntry();
        }
       return entry.getValue();
    }
    private int getHash(Object key) {
        return key.hashCode();
    }
}
