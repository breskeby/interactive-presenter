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


    GradleRemoteRun prepare(String snippetID) {
        GradleRemoteRun run = new GradleRemoteRun(snippetID)
        run.registerOutputChannel(channel);
        run
    }
}
