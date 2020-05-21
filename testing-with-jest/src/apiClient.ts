const fetch = require('node-fetch');

export class ApiClient {
  getTodo = (id: number): Promise<object> => {
    return fetch(`https://jsonplaceholder.typicode.com/todos/${id}`)
      .then(response => response.json())
  }
}
