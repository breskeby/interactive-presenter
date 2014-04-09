'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'phonecatAnimations',
  'snippetControllers',
  'phonecatFilters',
  'snippetServices',
  'MyApp'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/snippets', {
        templateUrl: 'partials/snippet-list.html',
        controller: 'PhoneListCtrl'
      }).
      /*when('/snippets/:phoneId', {
        templateUrl: 'partials/snippet-detail.html',
        controller: 'PhoneDetailCtrl'
      }).*/
	  when('/snippets/:phoneId', {
        templateUrl: 'partials/snippet-slide.html',
        controller: 'PhoneDetailCtrl'
      }).
      otherwise({
        redirectTo: '/snippets'
      });
  }]);
