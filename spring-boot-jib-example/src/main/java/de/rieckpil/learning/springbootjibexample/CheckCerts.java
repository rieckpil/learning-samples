package de.rieckpil.learning.springbootjibexample;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;

public class CheckCerts {

    public static void main(String[] args) {
        String https_url = "https://www.google.com/";
        URL url;

        try {
            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.connect();

            Certificate[] certs = con.getServerCertificates();

            for (Certificate cert : certs) {

                System.out.println(cert.toString());

            }
            System.out.println("Successfully connected");

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
