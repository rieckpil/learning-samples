import angular from "angular";

const CustomersController = ($scope, customersFactory) => {
  $scope.sortBy = 'name';
  $scope.reverse = false;
  $scope.customers = [];

  const init = () => {
    $scope.customers = customersFactory.getCustomers();
  }

  init();

  $scope.doSort = (propName) => {
    console.log('Bar');
    $scope.sortBy = propName;
    $scope.reverse = !$scope.reverse;
  };
};

CustomersController.$inject = ['$scope', 'customersFactory'];

angular
  .module('orderApp')
  .controller('CustomersController', CustomersController);
