const request = require('supertest');

import {app} from '../src/app';

describe("test simple Express server", () => {

  it('should get a todo from root endpoint', async () => {
    await request(app)
      .get('/')
      .set('Accept', 'application/json')
      .expect('Content-Type', /json/)
      .expect(200);
  });

});
