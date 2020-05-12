package com.bl.h2;

import jdk.jshell.execution.Util;

public class SavingsCalculator {
    private final float[] credits;
    private final float[] debits;

    public SavingsCalculator(float[] credits, float[] debits) {
        this.credits = credits;
        this.debits = debits;
    }

    public float calculate() {
        return sumOfCredits() - sumOfDebits();
    }

    private float sumOfCredits() {
        float sum = 0.0f;
        for (float credit : credits) {
            sum += credit;
        }
        return sum;
    }

    private float sumOfDebits() {
        float sum = 0.0f;
        for (float debit : debits) {
            sum += debit;
        }
        return sum;
    }

    private static boolean allAmountsAreValid(float[] amounts) {
        for (float amount : amounts) {
            if (amount <= 0.0f) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: savingsCalculator <credits separated by ','> <debits separated by ','>");
            System.exit(-1);
        }

        final String[] creditsAsString = args[0].split(",");
        final String[] debitsAsString = args[1].split(",");

        final float[] credits = new float[creditsAsString.length];
        final float[] debits = new float[debitsAsString.length];

        for (int i = 0; i < creditsAsString.length; i++) {
            credits[i] = Utilities.getFloatValue(creditsAsString[i]);
        }

        for (int i = 0; i < debitsAsString.length; i++) {
            debits[i] = Utilities.getFloatValue(debitsAsString[i]);
        }

        if (!allAmountsAreValid(credits)) {
            System.out.println("All credit amounts must be >=0.0");
            return;
        }

        if (!allAmountsAreValid(debits)) {
            System.out.println("All debit amounts must be >=0.0");
            return;
        }

        final SavingsCalculator calculator = new SavingsCalculator(credits, debits);

        System.out.println("Net Savings = " + calculator.calculate());
    }
}
