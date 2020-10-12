package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static String[] readFile(String filename) {
        try {
            var file = new File(filename);
            if (!file.exists() || !file.isFile()) {
                System.err.println("File " + filename + " was not found.");
                return null;
            } else {
                var lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                return lines.toArray(new String[0]);
            }
        } catch (Exception e) {
            System.err.println("Something went wrong.");
            return null;
        }
    }

    public static void writeToFile(String source, String filename) throws IOException {
        try {
            var file = new File(filename);
            if (file.exists() && file.isFile()) {
                throw new FileAlreadyExistsException("File already exists.");
            }

            Files.write(Paths.get(filename), Collections.singleton(source), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw new IOException("Something went wrong:\n" + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            printSignature();
        }

        var lines = readFile(args[0]);
        if (lines == null) {
            System.exit(1);
            return;
        }

        var parser = new Deserializer();
        try {
            var m1 = parser.deserialize(lines);
            lines = Arrays.copyOfRange(lines, m1.getHeight() + 1, lines.length);
            var m2 = parser.deserialize(lines);
            if (lines.length > m2.getHeight() + 1)
                System.err.println("Extra information found.");
            var m3 = m1.dot(m2);
            var serializer = new Serializer();
            writeToFile(serializer.serialize(m3), args[1]);
            System.out.println("Successfully saved to 'out.txt'.");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    private static void printSignature() {
        System.out.println("Command signature: ");
        System.out.println("  <input.txt> <output.txt>");
    }
}
