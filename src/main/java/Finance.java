import java.util.Map;

public class Finance {

    public static final Map<String, String> commandsToUsage = Map.of(
            "bestLoanRates", "usage: BestLoanRates <numberOfYears>",
            "savingsCalculator", "usage: SavingsCalculator <credits separated by ','> <debits separated by ','>",
            "mortgageCalculator", "usage: MortgageCalculator <loanAmount> <termInYears> <annualRate>"
    );

    /*
        1. Add 2 argument check
        2. Call the different methods
     */
    public static void main(String[] args) {
        final String command = args[0];
        if (!commandsToUsage.containsKey(command)) {
            System.out.println(command + ": command not found");
            System.exit(-1);
        }


        final boolean isValidCommand = validateCommandArguments(args);
        if (!isValidCommand) {
            System.out.println(commandsToUsage.get(args[0]));
            System.exit(-1);
        }
    }

    private static boolean validateCommandArguments(String[] args) {
        switch (args[0]) {
            case "bestLoanRates":
                return args.length == 2;
            case "savingsCalculator":
                return args.length == 3;
            case "mortgageCalculator":
                return args.length == 4;
        }
        return false;
    }
}
