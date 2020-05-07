package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

public class ThumbnailHandler implements RequestHandler<S3Event, Void> {

  @Override
  public Void handleRequest(S3Event s3Event, Context context) {
    String bucket = s3Event.getRecords().get(0).getS3().getBucket().getName();
    String key = s3Event.getRecords().get(0).getS3().getObject().getKey();
    System.out.println("Going to create a thumbnail for: " + bucket + "/" + key);

    AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
    System.out.println("Connection to S3 established");

    S3Object s3object = s3Client.getObject(bucket, key);
    System.out.println("Successfully read S3 object with lenght: " + s3object.getObjectMetadata().getContentLength());

    // create thumbnail and upload it again

    return null;
  }
}
