package com.bl.h2;

import java.util.Scanner;

public class CommandLineApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);


        System.out.println("Enter your age");
        int age = scanner.nextInt();
        System.out.println("Your Age: " + age);

        scanner.close();
    }
}
