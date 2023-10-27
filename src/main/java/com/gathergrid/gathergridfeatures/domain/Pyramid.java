package com.gathergrid.gathergridfeatures.domain;

public class Pyramid {
    public String createPyramid(int levels) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= levels; i++) {
            for (int j = 1; j <= i; j++) {
                result.append("*");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
