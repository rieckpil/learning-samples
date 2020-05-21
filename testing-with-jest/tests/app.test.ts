import {app} from '../src/app';

const request = require('supertest');

const mockGetTodo = jest.fn();

jest.mock('../src/apiClient', () => {
  return {
    ApiClient: jest.fn().mockImplementation(() => {
      return {
        getTodo: jest.fn()
      }
    })
  }
});

describe("test simple Express server", () => {

  beforeEach(() => {
    mockGetTodo.mockClear();
  });

  afterEach(() => {
    console.log('after each');
  })

  it('should get a todo from root endpoint', async () => {
    const response = await request(app)
      .get('/')
      .set('Accept', 'application/json')
      .expect('Content-Type', /json/)
      .expect(200);

    console.log(response.body);
  });

});
