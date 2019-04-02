package de.rieckpil.learning;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUpdate {

	public static void main(String[] args) throws UnsupportedEncodingException {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());

		System.out.println("### NEW with Java 9");
		System.out.println(resourceBundle.getString("hello"));
		System.out.println(resourceBundle.getString("world"));
		System.out.println(resourceBundle.getString("duke"));

		System.out.println("### OLD with Java 8");
		System.out.println(new String(resourceBundle.getString("hello").getBytes("ISO-8859-1")));
		System.out.println(new String(resourceBundle.getString("world").getBytes("ISO-8859-1")));
		System.out.println(new String(resourceBundle.getString("duke").getBytes("ISO-8859-1")));

	}
}
