package de.rieckpil.learning;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UnicodeSupport {

	public static void main(String[] args) throws IOException {

		System.out
				.println(new String(new char[] { Character.highSurrogate(0x1f605), Character.lowSurrogate(0x1f605) }));

		final String content = "<html><body>&#x00B0 &#x1801 &#x	1f605 &#x1f310</body></html>";

		final File tmpFile = File.createTempFile("Unicode_8_", ".html");

		try (final FileOutputStream fos = new FileOutputStream(tmpFile);
				final BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(content.getBytes());
			bos.flush();
		}

		if (Desktop.isDesktopSupported()) {
			final Desktop desktop = Desktop.getDesktop();

			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				desktop.browse(tmpFile.toURI());
			}
		}
	}

}
