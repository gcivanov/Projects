angular.module('mymod',['ngRoute'])
	.controller('UsersController', function($scope, $http, $location, $rootScope) {
		$http.get('rest/registration').success(function(data){
			$scope.users = data.user;
			$scope.myTest = data;
			//$rootScope.broadcast('Vzehme');
		});
		
		$scope.createNewUser = function(){
			$http.post('rest/registration', $scope.newUser).success(function(data){
				
				$location.path('/users');
			});
		};
		
	})
	
	.controller('ApplicationController', function ($scope,
                                               USER_ROLES,
                                               AuthService) {
		$scope.currentUser = null;
		$scope.userRoles = USER_ROLES;
		$scope.isAuthorized = AuthService.isAuthorized;
	})
	
	.controller('LoginController', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
		$scope.credentials = {
				username: '',
				password: ''
		};
		$scope.login = function (credentials) {
			AuthService.login(credentials).then(function () {
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
			}, function () {
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
			});
  		};
	})
	.factory('AuthService', function ($http, Session) {
		return {
			login: function (credentials) {
				return $http
				.post('rest/login', credentials)
				.then(function (res) {
					Session.create(res.id, res.userid, res.role);
				});
			},
			isAuthenticated: function () {
				return !!Session.userId;
			},
			isAuthorized: function (authorizedRoles) {
				if (!angular.isArray(authorizedRoles)) {
					authorizedRoles = [authorizedRoles];
				}
				return (this.isAuthenticated() &&
						authorizedRoles.indexOf(Session.userRole) !== -1);
			}
		};
	})
	.service('Session', function () {
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
	})
	.constant('USER_ROLES', {
		all: '*',
		editor: 'editor',
		regular: 'regular',
		guest: 'guest'
	})
	.constant('AUTH_EVENTS', {
		loginSuccess: 'auth-login-success',
		loginFailed: 'auth-login-failed',
		logoutSuccess: 'auth-logout-success',
		sessionTimeout: 'auth-session-timeout',
		notAuthenticated: 'auth-not-authenticated',
		notAuthorized: 'auth-not-authorized'
	})
	
	.config(['$routeProvider', function ($routeProvider) {
		$routeProvider.
			when('/registration', {
				templateUrl: "views/registration.html",
				controller: "UsersController"
			}).
			when('/users', {
				templateUrl: "views/listUsers.html",
				controller: "UsersController"
			}).
			when('/login', {
				templateUrl:"views/login.html",
				controller: "LoginController" //change this later
			}).
			otherwise({
				redirectTo: "/registration"
			});
	}]);