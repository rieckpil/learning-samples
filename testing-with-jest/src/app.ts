import express from 'express';
import {ApiClient} from "./apiClient";
import {S3Client} from "./s3Client";
import {EventEmitter} from 'events';

export const app = express();

const apiClient = new ApiClient();
const s3Client = new S3Client();
const profiles = new EventEmitter();

profiles.on('route', ({req, elapsedMS}) => {
  console.log(req.method, req.url, `${elapsedMS}ms`);
});

app.use((req, res, next) => {
  const start = Date.now();
  res.once('finish', () => {
    profiles.emit('route', {req, elapsedMS: Date.now() - start});
  });
  next();
});

app.get('/', async (req, res) => {

  const apiResult = apiClient.getTodo(2);
  const s3Result = s3Client.getFileUrl();

  res.send({
    ...await apiResult,
    createdAt: (await s3Result).LastModified
  });
});
