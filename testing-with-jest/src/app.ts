import express from 'express';
import {ApiClient} from "./apiClient";

const app = express();
const port = 3000;

const apiClient = new ApiClient();

app.get('/', async (req, res) => {
  const apiResult = await apiClient.getTodo(2);
  res.send(apiResult);
});

app.listen(port, err => {
  if (err) {
    return console.error(err);
  }
  return console.log(`server is listening on ${port}`);
});
