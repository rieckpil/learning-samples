import angular from 'angular';
import './style.css';
import 'angular-route';

const app = angular.module('orderApp', ['ngRoute']);

app.config(($routeProvider) => {
  $routeProvider
    .when('/', {
      controller: 'CustomersController',
      template: require('./views/customers.html')
    })
    .when('/orders/:customerId', {
      controller: 'OrdersController',
      template: require('./views/orders.html')
    })
    .otherwise({redirectTo: '/'});
});

app.config(['$locationProvider', ($locationProvider) => {
  $locationProvider.html5Mode(false);
  $locationProvider.hashPrefix('');
}]);

const appModules = require.context('.', true, /\.js$/);
appModules.keys()
  .forEach(appModules);

