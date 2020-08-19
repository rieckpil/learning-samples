const customerFactory = () => {
  const customers = [
    {
      id: 1, joined: '2000-12-02', name: 'John', city: 'Chandler', orderTotal: 9.9956,
      orders: [{id: 1, product: 'Shoes', total: 9.9956}]
    },
    {
      id: 2, joined: '1965-01-25', name: 'Zed', city: 'Las Vegas', orderTotal: 19.99,
      orders: [{id: 2, product: 'Hat', total: 19.99}]
    },
    {
      id: 3, joined: '1944-06-15', name: 'Tina', city: 'New York', orderTotal: 44.99,
      orders: [{id: 3, product: 'CD', total: 44.99}]
    },
    {
      id: 4, joined: '1995-03-28', name: 'Dave', city: 'Seattle', orderTotal: 101.50,
      orders: [{id: 4, product: 'Jacket', total: 101.50}]
    }
  ];

  const factory = {};

  factory.getCustomers = () => customers;

  factory.getCustomer = (customerId) => {
    for (let i = 0; i < customers.length; i++) {
      if (customers[i].id === parseInt(customerId)) {
        return customers[i];
      }
    }
    return {};
  }

  return factory;
}

angular
  .module('orderApp')
  .factory('customersFactory', customerFactory);
