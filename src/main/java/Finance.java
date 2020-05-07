import java.util.Map;

public class Finance {

    public static final Map<String, String> commandsToUsage = Map.of(
            "bestLoanRates", "usage: Finance bestLoanRates <numberOfYears>",
            "savingsCalculator", "usage: SavingsCalculator <credits separated by ','> <debits separated by ','>",
            "mortgageCalculator", "usage: MortgageCalculator <loanAmount> <termInYears> <annualRate>"
    );

    /*
        1. Add 2 argument check
        2. Call the different methods
     */
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
