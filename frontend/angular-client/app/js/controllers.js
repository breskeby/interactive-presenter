'use strict';

/* Controllers */

var snippetControllers = angular.module('snippetControllers', ['MyApp']);

snippetControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.snippets = Phone.query();
    $scope.orderProp = 'age';
  }]);

snippetControllers.controller('SnippetDetailController', ['$scope', 'Snippet', 'MyService',
  function($scope, Snippet, MyService) {
	 $scope.init = function(snippetId) {
	    //This function is sort of private constructor for controller
	    console.log(snippetId)
		$scope.snippetId = snippetId
	    //Based on passed argument you can make a call to resource
	    //and initialize more objects
	    //$resource.getMeBond(007)
		console.log("init method SnippetDetailControler " + snippetId)
		$scope.snippet = Snippet.get({phoneId: snippetId}, function(snippet) {
		});
	  };
	
	$scope.run = function() {
			console.log($scope.snippet.commandLine)
			$scope.customers = MyService.startBuild($scope.snippetId, $scope.snippet.commandLine, function(data) {
				console.log("callyaback");
				console.log(data.gevent.event.output)
				$scope.snippet.content = data.gevent.event.output;
				$scope.$apply(function(){
					console.log("run completed")
				});
		    });
		}

	$scope.reset = function() {
			$scope.snippet = Snippet.get({phoneId: $scope.snippetId}, function(snippet) {
				console.log("content: " + $scope.snippet.content)
				console.log("reset completed")
		    });
		}
  }]);

  
