package com.yupi.yurpc.service.tcp;

import com.yupi.yurpc.RpcApplication;
import com.yupi.yurpc.model.RpcRequest;
import com.yupi.yurpc.model.RpcResponse;
import com.yupi.yurpc.protocol.*;
import com.yupi.yurpc.registry.LocalRegistry;
import com.yupi.yurpc.serializer.Serializer;
import com.yupi.yurpc.serializer.SerializerFactory;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * TCP请求处理器
 */
public class TcpServerHandler implements Handler<NetSocket> {

    /**
     * 处理请求
     *
     * @param socket
     */
    @Override
    public void handle(NetSocket socket) {
       //处理连接
       TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
           //接受请求，进行解码
           ProtocolMessage<RpcRequest> protocolMessage;
           try {
               protocolMessage = (ProtocolMessage<RpcRequest>)
                       ProtocolMessageDecoder.decoder(buffer);
           } catch (IOException e) {
               throw new RuntimeException("协议消息解码错误");
           }
           RpcRequest rpcRequest = protocolMessage.getBody();
           //处理请求
           //构造响应结果
           RpcResponse rpcResponse = new RpcResponse();
           try {
               //获取调用方的实现类，通过反射进行调用
               Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
               //通过反射，getMethod方法来(方法名,参数类型)，注意implClass要是一个实例，不能是一个接口
               Method  method = implClass.getMethod(rpcRequest.getMethodName(),
                       rpcRequest.getParameterTypes());
               //invoke(调用类的实例，参数)
               Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
               //拿到结果封装为response->byte[]->buffer-传递出去
               rpcResponse.setData(result);
               rpcResponse.setDataType(method.getReturnType());
               rpcResponse.setMessage("ok");
           } catch (Exception e) {
               e.printStackTrace();
               rpcResponse.setMessage(e.getMessage());
               rpcResponse.setException(e);
           }

           //发送响应，编码
           ProtocolMessage.Header header = protocolMessage.getHeader();
           header.setType((byte) ProtocolMessageTypeEnum.RESPONSE.getKey()); //因为header的其他信息都是一样的，唯一不同的是头的类型，在这里是RESPONSE
           ProtocolMessage<RpcResponse> responseProtocolMessage = new ProtocolMessage<>(header,rpcResponse);
           try {
               Buffer code = ProtocolMessageEncoder.encode(responseProtocolMessage);
               socket.write(code);
           } catch (IOException e) {
               throw new RuntimeException("协议消息编码错误");
           }
       });
       socket.handler(bufferHandlerWrapper);
    }

}
