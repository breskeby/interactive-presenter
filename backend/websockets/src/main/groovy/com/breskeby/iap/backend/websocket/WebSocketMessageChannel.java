package com.breskeby.iap.backend.websocket;

import com.breskeby.iap.backend.MessageChannel;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebSocketMessageChannel implements MessageChannel {
    private final Channel channel;

    public WebSocketMessageChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String getId() {
        return channel.id().asLongText();
    }

    @Override
    public void write(String content) {
        channel.writeAndFlush(new TextWebSocketFrame(content));
    }
}
