package com.company;

public class Serializer {
    public String serialize(Matrix matrix) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                result.append(matrix.getItem(i, j)).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
