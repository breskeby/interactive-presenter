'use strict';

/* Controllers */

var phonecatControllers = angular.module('snippetControllers', ['MyApp']);

phonecatControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.snippets = Phone.query();
    $scope.orderProp = 'age';
  }]);

phonecatControllers.controller('SnippetDetailController', ['$scope', 'Snippet', 'MyService',
  function($scope, Snippet, MyService) {
    $scope.snippet = Snippet.get({phoneId: $scope.snippetId}, function(snippet) {
		
    });

	$scope.run = function() {
		$scope.customers = MyService.getCustomers($scope.snippetId, function(data) {
			console.log("callyaback");
			console.log(data.gevent.event.output)
			$scope.snippet.content = data.gevent.event.output;
			$scope.$apply();
	    });
	}
  }]);

phonecatControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
  function($scope, $routeParams, Phone) {
    $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
      $scope.mainImageUrl = phone.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    }
  }]);
