package com.breskeby.iap.backend.gradle

/**
 * Created with IntelliJ IDEA.
 * User: Rene
 * Date: 04/12/13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
class JsonOutputFormatter {
    static String format(int sessionId, String unformatted){
        def builder = new groovy.json.JsonBuilder()
        def root = builder.gevent {
            callback_id sessionId
            event {
                output unformatted
            }
        }
        return builder.toString()
    }
}
