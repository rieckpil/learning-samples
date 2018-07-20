package de.rieckpil.learning.javabycomparison.exceptions;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TryWithResource {

    static final Path LOG_FOLDER = Paths.get("/var/log");
    static final String FILE_FILTER = "*.log";

    public static void main(String[] args) throws Exception {


        List<Path> result = getLogs();

        for (Path path : result) {
            System.out.println(path.getFileName() + " " + path.getFileSystem() + " " + path.getNameCount());
        }

    }

    private static List<Path> getLogs() throws IOException {

        List<Path> result = new ArrayList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER)) {
            for (Path logFile : directoryStream) {
                result.add(logFile);
            }
        }

        return result;
    }
}
