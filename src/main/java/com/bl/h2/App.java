package com.bl.h2;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello");

    }

    private static int privateMethod(int[] numbers) {
        var sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
