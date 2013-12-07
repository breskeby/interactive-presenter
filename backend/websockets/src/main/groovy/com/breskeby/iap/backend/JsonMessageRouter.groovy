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
            def preparedRun = remoteRunner.prepare(jsonRequest.snippetId, jsonRequest.callback_id)
            List<String> tasks = jsonRequest.tasks;
            def taskArray = tasks.toArray(new String[tasks.size()])
            preparedRun.run(taskArray)
        }else{
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
