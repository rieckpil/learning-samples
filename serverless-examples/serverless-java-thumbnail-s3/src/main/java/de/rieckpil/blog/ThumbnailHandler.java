package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Map;

public class ThumbnailHandler implements RequestHandler<Map<String, Object>, Void> {

  private static final Logger LOG = LogManager.getLogger(ThumbnailHandler.class);
  private static final Region region = Region.EU_CENTRAL_1;

  private S3Client s3Client;

  @Override
  public Void handleRequest(Map<String, Object> input, Context context) {
    LOG.info("received new S3 event: {}", input);

    S3Client s3 = S3Client.builder().region(region).build();

    //s3.getObject(GetObjectRequest.builder().bucket("bucket").key("key").build(),ResponseTransformer.toFile(Paths.get("multiPartKey")));

 /*   try {
      BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
      img.createGraphics().drawImage(ImageIO.read(new File("test.jpg")).getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, null);
      ImageIO.write(img, "jpg", new File("test_thumb.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
*/
    return null;
  }
}
