import {app} from './app';

const request = require('supertest');

const mockGetFileUrl = jest.fn();

jest.mock('./apiClient');
jest.mock('./s3Client', () => {
  return {
    S3Client: jest.fn().mockImplementation(() => {
      return {
        getFileUrl: jest.fn().mockImplementation(() => {
          return {LastModified: 'Duke'};
        })
      }
    })

  }
});

describe("test simple Express server", () => {

  beforeEach(() => {
    mockGetFileUrl.mockClear();
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
