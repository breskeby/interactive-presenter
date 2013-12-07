package com.breskeby.iap.backend.gradle.remote

import com.breskeby.iap.backend.MessageChannel
import com.breskeby.iap.backend.gradle.JsonOutputFormatter
import com.breskeby.iap.backend.gradle.ProjectWorkspace
import org.gradle.tooling.BuildException
import org.gradle.tooling.BuildLauncher
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProgressEvent
import org.gradle.tooling.ProgressListener
import org.gradle.tooling.ProjectConnection
import sun.launcher.resources.launcher

/**
 * Created with IntelliJ IDEA.
 * User: Rene
 * Date: 04/12/13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
class GradleRemoteRun {
    private String snippetId

    private ProjectWorkspace workspace
    MessageChannel outputChannel
    private int sessionId

    GradleRemoteRun(String snippetId, int sessionId) {
        //To change body of created methods use File | Settings | File Templates.
        this.sessionId = sessionId
        this.snippetId = snippetId
        File workspaceDirectory = new File("workspaces/$snippetId");
        workspaceDirectory.mkdirs()
        this.workspace = new ProjectWorkspace(projectDirectory: workspaceDirectory)
    }

    void run(String... tasks){

        // Configure the connector and create the connection
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(workspace.projectDirectory);

        ProjectConnection connection = connector.connect();
        OutputStream stream = new ByteArrayOutputStream()
        try {
            // Configure the build
            BuildLauncher launcher = connection.newBuild();
            launcher.forTasks()
            launcher.forTasks(tasks);

            launcher.setStandardOutput(stream)
            launcher.setStandardError(stream);

            // Run the build
            launcher.run();
        } finally {
            outputChannel.write(JsonOutputFormatter.format(sessionId, new String(stream.toByteArray())));
            connection.close();
        }
    }

    void registerOutputChannel(MessageChannel messageChannel) {
        this.outputChannel = messageChannel;
    }
}
