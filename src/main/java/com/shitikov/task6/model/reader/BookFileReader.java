package com.shitikov.task6.model.reader;

import com.shitikov.task6.model.exception.ProjectException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookFileReader {
    public String readFile(String fileName) throws ProjectException {
        String text = "";
        Path path = Paths.get(fileName);

        if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
            try (Stream<String> dataStream = Files.lines(path)) {
                text = dataStream.collect(Collectors.joining("\n"));
            } catch (IOException | UncheckedIOException e) {
                throw new ProjectException("Program error.", e);
            }
        }
        return text;
    }
}
