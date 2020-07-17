package de.rieckpil.learning;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailTest {

  @Test
  public void test() throws IOException {

    InputStream input = this.getClass().getResourceAsStream("/myPicture.png");
    BufferedImage srcImage = ImageIO.read(input);
    int width = 100;
    int height = 100;

    BufferedImage resizedImage = new BufferedImage(width, height,
      BufferedImage.TYPE_INT_RGB);
    Graphics2D g = resizedImage.createGraphics();
    // Fill with white before applying semi-transparent (alpha) images
    g.setPaint(Color.white);
    g.fillRect(0, 0, width, height);
    // Simple bilinear resize
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(srcImage, 0, 0, width, height, null);
    g.dispose();

    // Re-encode image to target format
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(resizedImage, ".png", os);
    InputStream is = new ByteArrayInputStream(os.toByteArray());
    System.out.println(os.size());

  }
}
