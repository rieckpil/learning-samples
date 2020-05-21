export class ApiClient {
  getTodo = (id: number) => {
    return new Promise(resolve => {
      resolve({
        id,
        name: 'Mark todo #1 as done'
      })
    })
  }
}
