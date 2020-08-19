import angular from "angular";

const OrdersController = ($scope, $routeParams, customersFactory) => {

  $scope.customer = null;

  function init() {
    $scope.customer = customersFactory.getCustomer($routeParams.customerId);
  }

  init();
};

OrdersController.$inject = ['$scope', '$routeParams', 'customersFactory'];

angular
  .module('orderApp')
  .controller('OrdersController', OrdersController);
