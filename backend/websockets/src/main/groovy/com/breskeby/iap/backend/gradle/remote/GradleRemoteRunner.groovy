package com.breskeby.iap.backend.gradle.remote

import com.breskeby.iap.backend.MessageChannel

/**
 * Created by Rene on 04/12/13.
 */
class GradleRemoteRunner {
    private MessageChannel channel

    public GradleRemoteRunner(MessageChannel channel){
        this.channel = channel
    }


    GradleRemoteRun prepare(String snippetID, int sessionId) {
        GradleRemoteRun run = new GradleRemoteRun(snippetID, sessionId)
        run.registerOutputChannel(channel);
        run
    }
}
