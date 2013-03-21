'use strict';

// Declare app level module which depends on filters, and services
var app = angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives']).
  config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $routeProvider.
        when('/', {templateUrl: 'partials/index.html', controller: EntriesListCtrl }).
        when('/person/:id', {templateUrl: 'partials/person-form.html', controller: PersonFormCtrl }).
		otherwise({redirectTo: '/'});
      
  }]);

