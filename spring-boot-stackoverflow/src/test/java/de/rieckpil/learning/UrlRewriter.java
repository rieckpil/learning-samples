package de.rieckpil.learning;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlRewriter implements ClientHttpRequestInterceptor {
  @Override
  public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
    System.out.println("inside rewriter");
    try {
      if (httpRequest.getURI().toString().contains("/def/abc")) {
        HttpRequest modifiedRequest = new MockClientHttpRequest(HttpMethod.GET, new URI("http://localhost:8888/def/abc"));
        return clientHttpRequestExecution.execute(modifiedRequest, bytes);
      } else {
        return clientHttpRequestExecution.execute(httpRequest, bytes);
      }

    } catch (URISyntaxException e) {
      e.printStackTrace();
      return null;
    }
  }
}
