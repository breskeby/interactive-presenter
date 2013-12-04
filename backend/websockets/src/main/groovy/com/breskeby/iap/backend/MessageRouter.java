package com.breskeby.iap.backend;

import com.breskeby.iap.backend.websocket.WebSocketMessageChannel;

public interface MessageRouter {
    void route(MessageChannel messageChannel, String request);
    void registerMessageChannel(MessageChannel messageChannel);
    void unregisterChannel(MessageChannel messageChannel);
}
