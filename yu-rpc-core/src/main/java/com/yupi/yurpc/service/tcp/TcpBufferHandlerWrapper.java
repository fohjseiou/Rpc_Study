package com.yupi.yurpc.service.tcp;

import com.yupi.yurpc.protocol.ProtocolConstant;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;

/**
 * 装修者模式(使用recordParse 对原有的buffer处理能力进行加强)
 */
public class TcpBufferHandlerWrapper implements Handler<Buffer> {
    private final RecordParser recordParser;

    public TcpBufferHandlerWrapper(Handler<Buffer> bufferHandler) {
        this.recordParser = initRecordParser(bufferHandler);
    }

    @Override
    public void handle(Buffer buffer) {
        recordParser.handle(buffer);
    }

    private RecordParser initRecordParser(Handler<Buffer> bufferHandler) {
        //构造parse
        RecordParser parser = RecordParser.newFixed(ProtocolConstant.MESSAGE_HEADER_LENGTH);

        parser.setOutput(new Handler<Buffer>() {
            //初始化
            int size = -1;
            //一次完整的读取()
            Buffer resultBuffer = Buffer.buffer();

            @Override
            public void handle(Buffer buffer) {
              if (-1 == size){
                  //读取消息体长度信息
                  size = buffer.getInt(13);
                  parser.fixedSizeMode(size);
                  //写入头信息到结果
                  resultBuffer.appendBuffer(buffer);
              }else {
                  //写入体信息到结果
                  resultBuffer.appendBuffer(buffer);
                  //已经拼接为完整的Buffer，执行结果
                  bufferHandler.handle(resultBuffer);
                  //重置一轮
                  parser.fixedSizeMode(ProtocolConstant.MESSAGE_HEADER_LENGTH);
                  size = -1;
                  resultBuffer = Buffer.buffer();
              }

            }
        });
        return parser;
    }
}
