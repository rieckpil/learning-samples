import angular from "angular";

const CustomersController = ($scope) => {
  $scope.sortBy = 'name';
  $scope.reverse = false;

  $scope.customers = [
    {id: 1, joined: '2000-12-02', name: 'John', city: 'Chandler', orderTotal: 9.9956},
    {id: 2, joined: '1965-01-25', name: 'Zed', city: 'Las Vegas', orderTotal: 19.99},
    {id: 3, joined: '1944-06-15', name: 'Tina', city: 'New York', orderTotal: 44.99},
    {id: 4, joined: '1995-03-28', name: 'Dave', city: 'Seattle', orderTotal: 101.50}
  ];

  $scope.doSort = (propName) => {
    console.log('Bar');
    $scope.sortBy = propName;
    $scope.reverse = !$scope.reverse;
  };
};

CustomersController.$inject = ['$scope'];

angular
  .module('orderApp')
  .controller('CustomersController', CustomersController);
