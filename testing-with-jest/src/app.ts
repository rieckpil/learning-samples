import express from 'express';
import {ApiClient} from "./apiClient";

export const app = express();

const apiClient = new ApiClient();

app.get('/', async (req, res) => {
  const apiResult = await apiClient.getTodo(2);
  res.send(apiResult);
});
