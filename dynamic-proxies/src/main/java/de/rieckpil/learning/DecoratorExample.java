package de.rieckpil.learning;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DecoratorExample {

    public static void main(String[] args) throws IOException {
        writeFile();
        readFile();
    }

    private static void readFile() throws IOException {
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new GZIPInputStream(
                                new FileInputStream("data.bin.gz"))))) {
            long total = 0;
            while (in.available() > 0) {
                total += in.readInt();
            }
            System.out.println("total = " + total);
        }

    }

    private static void writeFile() throws IOException {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("data.bin.gz"))))) {
            ThreadLocalRandom.current().ints(10_000_000, 0, 1_000)
                    .forEach(i -> {
                        try {
                            out.writeInt(i);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
    }
}
