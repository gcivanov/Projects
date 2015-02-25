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
		controller: "LoginController"
	}).
	when('/home', {
		templateUrl: "views/homePage.html",
		controller: "HomeController"
	}).
	when('/movies', {
		templateUrl: "views/movies.html",
		controller: "MovieContr"
	}).
	when('/rooms', {
		templateUrl: "views/rooms.html",
		controller: "RoomContr"
	}).
	when('/register', {
		templateUrl: "views/registration.html",
		controller: "Registration"
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

myapp.controller('MovieContr', function($scope, $http, $location, $rootScope) {
	$http.get('rest/movies').success(function(data){
		$scope.movies = data.movies;
		$scope.bobiTest = data;
	});
	
});

myapp.controller('RoomContr', function($scope, $http, $location, $rootScope) {
	$http.get('rest/rooms').success(function(data){
		$scope.rooms = data.rooms;
		$scope.bobiTest = data;
	});
	
});

myapp.controller('Registration', function($scope, $http, $location, $rootScope) {
//	$http.get('rest/rooms').success(function(data){
//		$scope.rooms = data.rooms;
//		$scope.bobiTest = data;
//	});
	
});


myapp.controller('LoginController', function($scope, $http, $location, LoginService) {
	//console.log("I'm here");
	$scope.checkUser = function(){
		//console.log($scope.logUser.user.username);
		LoginService.login($scope.logUser);
		
	};
});

myapp.controller('HomeController',  function($scope, $http, $location, LoginService, SessionService) {
	
	$scope.currentUser = SessionService.user;
	//console.log(SessionService.user);
	$scope.logout = function(){
		//console.log("Okie Dokie");
		LoginService.logout();
		
	};
});

myapp.factory('LoginService', function($http, $location, SessionService){
	return {
		login:function(data){
			if(data.user.username == "Bobi"){
//				SessionService.set(9, "Bobi", "Jedi");
				SessionService.create(9, "Bobi", "Jedi");
				$location.path('/home');
			}
			else {
				//console.log("Sorry Baby");
			}
			
			if(data.user.username == "Haki"){
//				SessionService.set(9, "Haki", "Jedi");
				SessionService.create(9, "Haki", "Jedi");
				$location.path('/home');
			}
			else {
				//console.log("Sorry Baby");
			}
		},
		
		isLogged:function(){
			if(SessionService.user == undefined){
				return false;
			}
			return true;
		},
		
		logout:function(){
//			SessionService.destroy();
			SessionService.destroy();
			$location.path('/login');
		}
	};
});


myapp.service('SessionService', function(){
//	return{
//		set:function(key, value, role){
//			this.id = key;
//			this.user = value;
//			this.role = role;
//			return this;
//		},
//		getUser:function(){
//			//return this.getItem(key);
//			return this.user;
//		},
//		getRole:function(){
//			//return this.getItem(key);
//			return this.role;
//		},
//		destroy:function(key){
//			//return this.removeItem(key);
//			this.id = null;
//			this.user = null;
//			this.role = null;
//			return this;
//		}
//	};
	this.create = function (sessionId, userId, userRole){
		this.id = sessionId;
		this.user = userId;
		this.role = userRole;
	};
	
	this.destroy = function(){
		this.id = null;
		this.user = null;
		this.role = null;
	};
	
	
	
	return this;
});

myapp.run(function($rootScope, $location, LoginService){
	$rootScope.$on('$routeChangeStart', function(){
		var location = $location.path();
		if( location != '/login' && location !='/register')
		{
			//console.log(LoginService.isLogged());
			if(!LoginService.isLogged()){
				$location.path("/login");
			}
		}
	});
});
