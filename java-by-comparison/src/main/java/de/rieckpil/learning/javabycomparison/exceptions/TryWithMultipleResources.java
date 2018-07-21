package de.rieckpil.learning.javabycomparison.exceptions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TryWithMultipleResources {

    static final Path LOG_FOLDER = Paths.get("/var/log");
    static final String FILE_FILTER = "*.log";
    static final Path STATISTICS_CSV = Paths.get("/tmp").resolve("stats.csv");

    public static void main(String[] args) throws IOException {

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER);
             BufferedWriter writer = Files.newBufferedWriter(STATISTICS_CSV)) {

            for (Path logFile : directoryStream) {
                String csvLine = String.format("%s, %d, %s",
                        logFile,
                        Files.size(logFile),
                        Files.getLastModifiedTime(logFile));
                writer.write(csvLine);
                writer.newLine();
            }

        }

    }
}
