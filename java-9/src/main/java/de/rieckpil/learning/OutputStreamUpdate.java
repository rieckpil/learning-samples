package de.rieckpil.learning;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class OutputStreamUpdate {

	public static void main(String[] args) throws IOException {

		final byte[] buffer = { 72, 65, 76, 76, 79 };
		final byte[] result = new ByteArrayInputStream(buffer).readAllBytes();

		System.out.println(new String(result));

		new ByteArrayInputStream(buffer).transferTo(System.out);
	}
}
