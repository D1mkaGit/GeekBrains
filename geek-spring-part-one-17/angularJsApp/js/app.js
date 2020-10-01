let SpringApp = angular.module('SpringApp', ['ngRoute']);
let userList = [];
let productList = [];

SpringApp.config(function ($routeProvider, $locationProvider, $sceProvider) {
    $sceProvider.enabled(false);
    $routeProvider
        .when('/' || '/users', {
            templateUrl: 'users.html',
            controller: 'UserListCtrl'
        })
        .when('/user/new', {
            templateUrl: 'user.html',
            controller: 'UserNewCtrl'
        })
        .when('/user/:id/edit', {
            templateUrl: 'user.html',
            controller: 'UserEditCtrl'
        })
        .when('/products', {
            templateUrl: 'products.html',
            controller: 'ProductListCtrl'
        })
        .when('/product/new', {
            templateUrl: 'product.html',
            controller: 'ProductNewCtrl'
        })
        .when('/product/:id/edit', {
            templateUrl: 'product.html',
            controller: 'ProductEditCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});

SpringApp.controller('UserListCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {

    $http.get("http://localhost:8080/mvc-app/api/v1/user/all")
        .then(resp => {
                userList = resp.data;
                $scope.users = resp.data;
            },
            resp => {
                console.error(response.data.errorMessage);
            });

    $scope.deleteUser = function (user) {
        console.info(user);

        $http.delete("http://localhost:8080/mvc-app/api/v1/user/" + user.id + "/id")
            .then(function successCallback() {
                alert("User has deleted Successfully");
                const index = $scope.users.indexOf(user);
                $scope.users.splice(index, 1);
            }, function errorCallback(response) {
                console.error(response.data.errorMessage);
                alert("Error. while deleting user Try Again!");
            });
    }
});

SpringApp.controller('UserNewCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {

    $scope.user = {id: null, login: null, password: null};

    $scope.submit = function (user) {
        console.info(user);

        $http.post("http://localhost:8080/mvc-app/api/v1/user", user)
            .then(resp => {
                    $location.path("/");
                },
                resp => {
                    console.error(response.data.errorMessage);
                });
    }
});

SpringApp.controller('UserEditCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {

    $scope.user = userList.find(usr => usr.id === parseInt($routeParams.id));

    $scope.submit = function (user) {
        console.info(user);

        $http.put("http://localhost:8080/mvc-app/api/v1/user", user)
            .then(resp => {
                    $location.path("/");
                },
                resp => {
                    console.error(response.data.errorMessage)
                });
    }
});

SpringApp.controller('ProductListCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {

    $http.get("http://localhost:8080/mvc-app/api/v1/product/all")
        .then(resp => {
                productList = resp.data;
                $scope.products = resp.data;
            },
            resp => {
                console.error(response.data.errorMessage);
            });

    $scope.deleteProduct = function (product) {
        console.info(product);

        $http.delete("http://localhost:8080/mvc-app/api/v1/product/" + product.id + "/id")
            .then(function successCallback() {
                alert("Product has deleted Successfully");
                const index = $scope.products.indexOf(product);
                $scope.products.splice(index, 1);
            }, function errorCallback(response) {
                console.error(response.data.errorMessage);
                alert("Error. while deleting product Try Again!");
            });
    }
});

SpringApp.controller('ProductNewCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {

    $scope.product = {id: null, title: null, cost: null};

    $scope.submit = function (product) {
        console.info(product);

        $http.post("http://localhost:8080/mvc-app/api/v1/product", product)
            .then(resp => {
                    $location.path("/products");
                },
                resp => {
                    console.error(response.data.errorMessage);
                });
    }
});

SpringApp.controller('ProductEditCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {

    $scope.product = productList.find(prd => prd.id === parseInt($routeParams.id));

    $scope.submit = function (product) {
        console.info(product);

        $http.put("http://localhost:8080/mvc-app/api/v1/product", product)
            .then(resp => {
                    $location.path("/products");
                },
                resp => {
                    console.error(response.data.errorMessage)
                });
    }
});