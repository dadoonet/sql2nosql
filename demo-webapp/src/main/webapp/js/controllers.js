'use strict';

/* Controllers */


function AppCtrl($scope, $http) {
	$scope.user = null;
}
AppCtrl.$inject = ['$scope', '$http'];


function NavBarController($scope) {
}
NavBarController.$inject = ['$scope'];

function EntriesListCtrl($scope, $http) {
    $scope.query = null;

    $scope.search = function() {
        $http({method: 'GET', url: '/api/1/person/_search?q='+ $scope.query }).success(function(data, status, headers, config) {
            $scope.entries = data;
        })
            .error(function(data, status, headers, config) {
                $scope.name = 'Error!'
            });
    }

    $scope.search();
}
EntriesListCtrl.$inject = ['$scope', '$http'];

function PersonFormCtrl($rootScope, $scope, $routeParams, $http, $location) {

    $http({method: 'GET', url: '/api/1/person/'+ $routeParams.id }).success(function(data, status, headers, config) {
        $scope.person = data;
    })
        .error(function(data, status, headers, config) {
            $scope.name = 'Error!'
        });


    $scope.save = function() {
        $http.put('/api/1/person/'+ $scope.person.id , $scope.person)
            .success(function(data, status, headers, config) { console.log( $scope.person ); })
            .error(function(data, status, headers, config) {
                $scope.name = 'Error!'
            });
    }



    $scope.delete = function() {
        $http({method: 'DELETE', url: '/api/1/person/'+ $routeParams.id }).success(function(data, status, headers, config) {
            $scope.person = data;
        })
            .error(function(data, status, headers, config) {
                $scope.name = 'Error!'
            });
        $location.path('/');
    }


}
PersonFormCtrl.$inject = ['$rootScope', '$scope', '$routeParams','$http', '$location'];