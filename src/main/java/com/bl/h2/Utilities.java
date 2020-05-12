package com.bl.h2;

public class Utilities {
    // float, long, int

    public static float getFloatValue(String in) {
        float out = Float.MIN_VALUE;
        try {
            out = Float.parseFloat(in);
        } catch (NumberFormatException e) {
            System.out.println(in + " cannot be converted into a 'float' value. Exiting program.");
        } finally {
            if (out != Float.MIN_VALUE) {
                // System.out.println(in + " is a valid 'float' value");
            }
        }
        return out;
    }

    public static long getLongValue(String in) {
        long out = Long.MIN_VALUE;
        try {
            out = Long.parseLong(in);
        } catch (NumberFormatException e) {
            System.out.println(in + " cannot be converted into a 'long' value. Exiting program.");
        }
        return out;

    }

    public static int getIntValue(String in) {
        int out = Integer.MIN_VALUE;
        try {
            out = Integer.parseInt(in);
        } catch (NumberFormatException e) {
            System.out.println(in + " cannot be converted into a 'float' value. Exiting program.");
        }
        return out;
    }
}
