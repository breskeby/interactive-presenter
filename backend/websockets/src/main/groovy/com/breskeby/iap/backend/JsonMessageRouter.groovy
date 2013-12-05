package com.breskeby.iap.backend

import com.breskeby.iap.backend.gradle.remote.GradleRemoteRunner;
import groovy.json.JsonSlurper;

public class JsonMessageRouter implements MessageRouter {
    private JsonSlurper slurper = new JsonSlurper()
    @Override
    public void route(MessageChannel messageChannel, String request) {
        def jsonRequest = slurper.parseText(request)

        if(jsonRequest.type == "startBuild"){
            GradleRemoteRunner remoteRunner = new GradleRemoteRunner(messageChannel)
            remoteRunner.prepare(jsonRequest.snippetId, jsonRequest.callback_id).run("tasks");
        }
    }

    @Override
    public void registerMessageChannel(MessageChannel messageChannel) {
        System.out.println("registering " + messageChannel.getId());

    }

    @Override
    public void unregisterChannel(MessageChannel messageChannel) {
        System.out.println("unregister " + messageChannel.getId());
    }
}
