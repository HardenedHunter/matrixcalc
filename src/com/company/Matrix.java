package com.company;

public class Matrix {
    private final int width;
    private final int height;
    private final float[][] data;

    Matrix(float[][] data) {
        if (data == null)
            throw new NullPointerException("data == null");

        this.data = data;
        this.height = data.length;
        this.width = data[0].length;
    }

    public Matrix dot(Matrix other) {
        if (other == null)
            throw new NullPointerException("other == null");
        if (this.width != other.height)
            throw new IllegalArgumentException("Dimensions don't match.");

        var result = new float[this.width][other.height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < other.width; j++) {
                float sum = 0;
                for (int k = 0; k < width; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%.2f ", this.data[i][j]);
            }
            System.out.println();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getItem(int i, int j) {
        return data[i][j];
    }
}
