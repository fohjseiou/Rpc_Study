package com.yupi.yurpc.service.tcp;

import com.yupi.yurpc.service.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

/**
 * TCP服务端
 */
public class VertxTcpServer  implements HttpServer {
    @Override
    public void doStart(int port) {
        //创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        //创建TCP服务器
        NetServer server = vertx.createNetServer();

        //处理请求
        server.connectHandler(new TcpServerHandler());


        //启动TCP服务器并且监听指定端口
        server.listen(port,result -> {
           if (result.succeeded()){
               System.out.println("TCP server started on port"+port);
           }else {
               System.out.println("Failed to start TCP server"+result.cause());
           }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
