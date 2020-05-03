package com.bl.h2;

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
        return -1;
    }

    private float getMonthlyInterestRate() {
        return 0.0f;
    }

    public float getMonthlyPayment() {
        return 0.0f;
    }

    public static void main(String[] args) {

    }
}
