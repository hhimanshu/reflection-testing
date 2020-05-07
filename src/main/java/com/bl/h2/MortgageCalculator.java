package com.bl.h2;

import java.text.DecimalFormat;

public class MortgageCalculator {
    private final long loanAmount;
    private final int termInYears;
    private final float annualRate;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments() {
        return this.termInYears * 12;
    }

    private float getMonthlyInterestRate() {
        float interestRate = annualRate / 100;
        return interestRate / 12;
    }

    public double getMonthlyPayment() {
        long P = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();

        System.out.println("P=" + P);
        System.out.println("r=" + r);
        System.out.println("n=" + n);
        double M = P * (((r * Math.pow(1 + r, n))) / ((Math.pow((1 + r), n)) - 1));

        return M;
    }

    public static void main(String[] args) {
        final MortgageCalculator c = new MortgageCalculator(264000, 30, 3.74f);
        final double monthlyPayment = c.getMonthlyPayment();
        DecimalFormat df = new DecimalFormat("####0.00");

        System.out.println("Monthly Payment: " + df.format(monthlyPayment));
    }
}
