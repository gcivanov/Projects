myapp = angular.module('mainCon',['ngRoute']);

myapp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider.
	when('/reservations', {
		templateUrl: "views/reservations.html",
		controller: "ReservController"
	}).
	when('/users', {
		templateUrl: "views/listUsers2.html",
		controller: "UsersController"
	}).
	when('/projections', {
		templateUrl: "views/projections.html",
		controller: "ProjectionContr"
	}).
	when('/login', {
		templateUrl: "views/login.html",
		controller: "loginCtrl"
	}).
	when('/home', {
		templateUrl: "views/homePage.html",
		controller: "homeCtrl"
	}).
	otherwise({
		redirectTo: "/login"
	});
}]);

myapp.controller('UsersController', function($scope, $http, $location, $rootScope) {
	$http.get('rest/users').success(function(data){
		$scope.myUsers = data.users;
	});
	
});

myapp.controller('ReservController', function($scope, $http, $location, $rootScope) {
	$http.get('rest/reservations').success(function(data){
		$scope.reservations = data.reservations;
		$scope.test = data;
	});
	
	$scope.buttonTest = function(){
		console.log($scope.myTestLog);
	};
	
});

myapp.controller('ProjectionContr', function($scope, $http, $location, $rootScope) {
	$http.get('rest/projections').success(function(data){
		$scope.projections = data.allProjections;
		$scope.bobiTest = data;
	});
	
});


myapp.controller('loginCtrl', ['$scope','loginService', function ($scope,loginService) {
	$scope.msgtxt='';
	$scope.login=function(data){
		loginService.login(data,$scope); //call login service
	};
}]);

myapp.controller('homeCtrl', ['$scope','loginService', function($scope,loginService){
	$scope.txt='Page Home';
	$scope.logout=function(){
		loginService.logout();
	};
}]);


myapp.factory('loginService',function($http, $location, sessionService){
	return{
		login:function(data,scope){
			var $promise=$http.post('data/user.php',data); //send data to user.php //important to change
			$promise.then(function(msg){
				var uid=msg.data;
				if(uid){
					//scope.msgtxt='Correct information';
					sessionService.set('uid',uid);
					$location.path('/home');
				}	       
				else  {
					scope.msgtxt='incorrect information';
					$location.path('/login');
				}				   
			});
		},
		logout:function(){
			sessionService.destroy('uid');
			$location.path('/login');
		},
		islogged:function(){
			//var $checkSessionServer=$http.post('data/check_session.php');  //may change later
//			return $checkSessionServer;
			/*
			if(sessionService.get('user')) return true;
			else return false;
			*/
		}
	};

});

myapp.factory('sessionService', ['$http', function($http){
	return{
		set:function(key,value){
			return sessionStorage.setItem(key,value);
		},
		get:function(key){
			return sessionStorage.getItem(key);
		},
		destroy:function(key){
			//$http.post('data/destroy_session.php');
			return sessionStorage.removeItem(key);
		}
	};
}]);

myapp.run(function($rootScope, $location, loginService){
	var routespermission=['/home'];  //route that require login
	$rootScope.$on('$routeChangeStart', function(){
		if( routespermission.indexOf($location.path()) !=-1)
		{
//			var connected=loginService.islogged();
//			connected.then(function(msg){
//				if(!msg.data) $location.path('/login');
//			});
		}
	});
});