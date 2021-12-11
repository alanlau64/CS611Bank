import java.util.Locale;

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
            default -> {
                return null;
            }
        }
    }
}
