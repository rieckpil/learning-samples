package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailHandler implements RequestHandler<S3Event, Void> {

  private static final Integer THUMBNAIL_SIZE = Integer.valueOf(System.getenv("THUMBNAIL_SIZE"));
  private static final String THUMBNAIL_PREFIX = "thumbnails/" + THUMBNAIL_SIZE + "x" + THUMBNAIL_SIZE + "-";

  @Override
  public Void handleRequest(S3Event s3Event, Context context) {
    String bucket = s3Event.getRecords().get(0).getS3().getBucket().getName();
    String key = s3Event.getRecords().get(0).getS3().getObject().getKey();
    System.out.println("Going to create a thumbnail for: " + bucket + "/" + key);

    AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
    System.out.println("Connection to S3 established");

    try {
      S3Object s3Object = s3Client.getObject(bucket, key);
      BufferedImage sourceImage = ImageIO.read(s3Object.getObjectContent());
      System.out.println("Successfully read S3 object");

      BufferedImage thumbnailImage = new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, BufferedImage.TYPE_INT_RGB);
      thumbnailImage.createGraphics().drawImage(sourceImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, null);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ImageIO.write(thumbnailImage, ".png", outputStream);
      System.out.println("Successfully created resized image");

      InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
      ObjectMetadata meta = new ObjectMetadata();
      meta.setContentLength(outputStream.size());
      meta.setContentType("image/png");

      String targetKey = THUMBNAIL_PREFIX + key.replace("uploads/", "");
      s3Client.putObject(bucket, targetKey, inputStream, meta);
      System.out.println("Successfully uploaded resized image with key " + targetKey);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
