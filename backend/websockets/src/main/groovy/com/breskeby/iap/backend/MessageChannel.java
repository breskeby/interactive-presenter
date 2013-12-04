package com.breskeby.iap.backend;

/**
 * Created by Rene on 04/12/13.
 */
public interface MessageChannel {
    String getId();

    void write(String content);
}
