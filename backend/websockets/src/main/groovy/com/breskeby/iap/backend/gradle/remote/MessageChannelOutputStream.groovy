package com.breskeby.iap.backend.gradle.remote

import com.breskeby.iap.backend.MessageChannel
import groovy.transform.Field
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame

/**
 * Created with IntelliJ IDEA.
 * User: Rene
 * Date: 04/12/13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
class MessageChannelOutputStream extends OutputStream {
    private OutputStream outputStream;
    private MessageChannel channel

    public ProgressLoggingOutputStream(MessageChannel channel){
        this.channel = channel
        this.outputStream = new ByteArrayOutputStream();
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
        channel.write(new TextWebSocketFrame(new String(outputStream)))
        outputStream = new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }
}