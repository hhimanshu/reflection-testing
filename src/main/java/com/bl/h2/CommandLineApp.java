package com.bl.h2;

import java.io.Console;

public class CommandLineApp {
    public static void main(String[] args) {
        final Console c = System.console();
        if (c == null) {
            System.err.println("console not available");
            System.exit(1);
        }

        String userName = c.readLine("Enter your name: ");
        System.out.println("Hello " + userName);
    }
}
