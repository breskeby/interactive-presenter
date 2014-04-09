'use strict';

/* Services */

var snippetServices = angular.module('snippetServices', ['ngResource']);

snippetServices.factory('Phone', ['$resource',
  function($resource){
    return $resource('snippets/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'snippets'}, isArray:true}
    });
  }]);

var snippetServices = angular.module('snippetServices', ['ngResource']);

snippetServices.factory('Snippet', ['$resource',
  function($resource){
    return $resource('snippets/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'snippets'}, isArray:true}
    });
  }]);



angular.module('MyApp', []).factory('MyService', ['$q', '$rootScope', function($q, $rootScope) {
    // We return this object to anything injecting our service
    var Service = {};
    // Keep all pending requests here until they get responses
    var callbacks = {};
	var myCallbacks = {};
    // Create a unique callback ID to map requests to responses
    var currentCallbackId = 0;
    // Create our websocket object with the address to the websocket
    var ws = new WebSocket("ws://localhost:8080/websocket/");

    ws.onopen = function(){  
        console.log("Socket has been opened!");  
    };
	
    ws.onmessage = function(message) {
        console.log("onMessage " + message.data)
		listener(JSON.parse(message.data));
    };

    function sendRequest(request, snippetCallback) {
      var defer = $q.defer();
      var callbackId = getCallbackId();
      callbacks[callbackId] = {
        time: new Date(),
        cb:defer
      };
      myCallbacks[callbackId] = snippetCallback;
      request.callback_id = callbackId;
      console.log('Sending request', request);
      ws.send(JSON.stringify(request));
      return defer.promise;
    }

    function listener(data) {
      var messageObj = data;
      console.log("Received data from websocket: " + messageObj.gevent.callback_id);
	  myCallbacks[messageObj.gevent.callback_id](messageObj);
      // If an object exists with callback_id in our callbacks object, resolve it
      if(callbacks.hasOwnProperty(messageObj.callback_id)) {
        console.log(callbacks[messageObj.callback_id]);
        $rootScope.$apply(callbacks[messageObj.callback_id].cb.resolve(messageObj.data));
        delete callbacks[messageObj.callbackID];
      }
    }
    // This creates a new callback ID for a request
    function getCallbackId() {
      currentCallbackId += 1;
      if(currentCallbackId > 10000) {
        currentCallbackId = 0;
      }
      return currentCallbackId;
    }

    // Define a "getter" for getting customer data
    Service.startBuild = function(snippetId, taskNames, snippetCallback) {
      var request = {
        type: "startBuild",
		snippetId: snippetId,
		tasks:[taskNames] //TODO must no be hardcoded but passed from the model
      }
      // Storing in a variable for clarity on what sendRequest returns
      var promise = sendRequest(request, snippetCallback); 
      return promise;
    }

    return Service;
}])