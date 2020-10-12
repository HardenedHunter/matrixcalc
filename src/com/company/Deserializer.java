package com.company;

import java.text.ParseException;

public class Deserializer {
    public Matrix deserialize(String[] source) throws ParseException {
        if (source == null)
            throw new NullPointerException("source == null");
        if (source.length < 2)
            throw new ParseException("Illegal number of lines.", 0);

        int width, height;
        String[] dims = source[0].trim().split(" ");

        if (dims.length != 2)
            throw new ParseException("Number of dimensions != 2.", 0);
        try {
            height = Integer.parseInt(dims[0]);
            width = Integer.parseInt(dims[0]);
        } catch (NumberFormatException ex) {
            throw new ParseException("Failed to parse dimensions.", 0);
        }

        if (source.length < height + 1)
            throw new ParseException("Illegal number of lines.", 0);

        var result = new float[height][width];
        for (int i = 0; i < height; i++) {
            String[] items = source[i + 1].trim().split(" ");
            if (items.length != width)
                throw new ParseException("Illegal number of items.", i + 1);
            try {
                for (int j = 0; j < width; j++)
                    result[i][j] = Float.parseFloat(items[j]);
            } catch (NumberFormatException ex) {
                throw new ParseException("Failed to parse items.", i + 1);
            }
        }

        return new Matrix(result);
    }
}
