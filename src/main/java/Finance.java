import java.util.Map;

public class Finance {

    // add 2 more rates. Add tests
    public static final Map<String, String> commandsToUsage = Map.of(
            "bestLoanRates", "usage: Finance bestLoanRates <numberOfYears>"
    );

    /*
        1. Add 2 argument check
        2. Call the different methods
     */
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
