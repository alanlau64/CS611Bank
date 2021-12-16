import java.util.Locale;

// Factory class to create log instances.
public class LogFactory {
    public Log getLog (String logType) {
        switch (logType.toLowerCase()) {
            case "customer" -> {
                return new CustomerLog();
            }
            case "manager" -> {
                return new ManagerLog();
            }
            case "transaction" -> {
                return new TransactionLog();
            }
            case "stock" -> {
                return new StockLog();
            }
            case "stocktrade" -> {
                return new StockTradeLog();
            }
            case "accountactivity" -> {
                return new AccountActivityLog();
            }
            case "loanactivity" -> {
                return new LoanActivityLog();
            }
            default -> {
                return null;
            }
        }
    }
}
