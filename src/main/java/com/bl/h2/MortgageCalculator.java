package com.bl.h2;

import java.text.DecimalFormat;

public class MortgageCalculator {
    private final long loanAmount;
    private final int termInYears;
    private final float annualRate;
    private double monthlyPayment;

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

    public void calculateMonthlyPayment() {
        long P = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();

        System.out.println("P=" + P);
        System.out.println("r=" + r);
        System.out.println("n=" + n);
        double M = P * (((r * Math.pow(1 + r, n))) / ((Math.pow((1 + r), n)) - 1));

        this.monthlyPayment = M;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("usage: mortgageCalculator <loanAmount> <termInYears> <annualRate>");
            System.exit(-1);
        }

        final long loanAmount = Utilities.getLongValue(args[0]);
        final int termInYears = Utilities.getIntValue(args[1]);
        final float annualRate = Utilities.getFloatValue(args[2]);
        final MortgageCalculator c = new MortgageCalculator(loanAmount, termInYears, annualRate);

        c.calculateMonthlyPayment();

        System.out.println("Monthly Payment: " + c.toString());
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");
        return "monthlyPayment: " + df.format(monthlyPayment);
    }
}
