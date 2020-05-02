package com.bl.h2;

public class SavingsCalculator {
    private float[] credits;
    private float[] debits;

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

    public static void main(String[] args) {
        final String[] creditsAsString = args[0].split(",");
        final String[] debitsAsString = args[1].split(",");

        final float[] credits = new float[creditsAsString.length];
        final float[] debits = new float[debitsAsString.length];

        for (int i = 0; i < creditsAsString.length; i++) {
            credits[i] = Float.parseFloat(creditsAsString[i]);
        }

        for (int i = 0; i < debitsAsString.length; i++) {
            debits[i] = Float.parseFloat(debitsAsString[i]);
        }

        final SavingsCalculator calculator = new SavingsCalculator(credits, debits);

        System.out.println("Net Savings = " + calculator.calculate());
    }
}
