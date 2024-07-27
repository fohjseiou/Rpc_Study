package com.yupi.yurpc.protocol;

import com.yupi.yurpc.serializer.Serializer;
import com.yupi.yurpc.serializer.SerializerFactory;
import io.vertx.core.buffer.Buffer;

import java.io.IOException;

/**
 * 编码
 */
public class ProtocolMessageEncoder {
  //1.创建方法
    public static Buffer encode(ProtocolMessage<?> protocolMessage) throws IOException {
       if (protocolMessage == null || protocolMessage.getHeader() == null){
         return Buffer.buffer(); //直接返回空
       }
       //消息体加消息头的组合
      ProtocolMessage.Header header = protocolMessage.getHeader();
      // 依次向缓冲区写入字节
      Buffer buffer = Buffer.buffer();
      buffer.appendByte(header.getMagic());
      buffer.appendByte(header.getVersion());
      buffer.appendByte(header.getSerializer());
      buffer.appendByte(header.getType());
      buffer.appendByte(header.getStatus());
      buffer.appendLong(header.getRequestId());
      // 获取序列化器
      ProtocolMessageSerializerEnum serializerEnum = ProtocolMessageSerializerEnum.getEnumByKey(header.getSerializer());
      if (serializerEnum == null) {
        throw new RuntimeException("序列化协议不存在");
      }
      Serializer serializer = SerializerFactory.getInstance(serializerEnum.getValue());
      byte[] bodyBytes = serializer.serializer(protocolMessage.getBody());
      // 写入 body 长度和数据
      buffer.appendInt(bodyBytes.length);
      buffer.appendBytes(bodyBytes);
      return buffer;

    }

}
