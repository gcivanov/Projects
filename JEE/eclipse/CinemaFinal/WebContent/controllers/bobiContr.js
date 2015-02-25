myapp = angular.module('mymod',['ngRoute']);

myapp.controller('UsersController', function($scope, $http, $location, $rootScope) {
	$http.get('rest/registration').success(function(data){
		$scope.users = data.user;
		//$scope.myTest = data;
		//$rootScope.$emit('Vzehme');
	});
	
	$scope.createNewUser = function(){
		$http.post('rest/registration', $scope.newUser)
		.success(function(data){
			$location.path('/users');
		});
	};
	
});

myapp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider.
	when('/registration', {
		templateUrl: "views/registration.html",
		controller: "UsersController"
	}).
	when('/users', {
		templateUrl: "views/listUsers2.html",
		controller: "UsersController"
	}).
	when('/login', {
		templateUrl:"views/login2.html",
		controller: "LoginController" //change this later
	}).
	when('/main',{
		templateUrl: "views/mainMenu.html",
		controller: "LoginController"
	}).
	otherwise({
		redirectTo: "/login"
	});
}]);


myapp.constant('USER_ROLES', {
	all: '*',
	editor: 'editor',
	regular: 'Regular',
	guest: 'guest'
});

myapp.constant('AUTH_EVENTS', {
	loginSuccess: 'auth-login-success',
	loginFailed: 'auth-login-failed',
	logoutSuccess: 'auth-logout-success',
	sessionTimeout: 'auth-session-timeout',
	notAuthenticated: 'auth-not-authenticated',
	notAuthorized: 'auth-not-authorized'
});

myapp.controller('LoginController',function($scope, $http, $location, Session){
	/*$scope.credentials ={
		username:'',
		password:''
	}; /**/
	/*$scope.storeUser={
		username:'',
		password:'',
		role:''
	}; /**/
	
	//$scope.myUser.user.username = $scope.credentials.username; // ne moje takiva gluposti da se pi6at
	//$scope.myUser.user.password = $scope.credentials.password;
	
	$scope.myTest = 2000;
	
	$scope.checkUser = function(){
		$scope.myTest = 1000;
		$http.post('rest/login', $scope.credentials).success(function(data){
			//$scope.storeUser = data.user;
			Session.create(1, data.user.username, data.user.role);
			$location.path('/main');
		});
		
	};
	
	$scope.makeMe = function(){
		
	};
});

	//Nerabote6to o6te
	myapp.service('Session', function () {
		this.create = function (sessionId, userId, userRole) {
			this.id = sessionId;
		    this.userId = userId;
		    this.userRole = userRole;
		 };
		 this.destroy = function () {
		    this.id = null;
		    this.userId = null;
		    this.userRole = null;
		  };
		  return this;
	});