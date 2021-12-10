import java.util.HashMap;
import java.util.Map;

public class SecuritiesAccount extends Account{
    private Map<Stock, Integer> stocks;

    public SecuritiesAccount(){}

    public SecuritiesAccount(String userName){
        super(userName);
        withdrawTransaction = new WithdrawWithTransactionFee();
        depositTransaction = new DepositWithoutTransactionFee();
        transferTransaction = new TransferWithoutTransactionFee();
        stocks = new HashMap<>();
    }

    public Map<Stock, Integer> getStocks() {
        return stocks;
    }
}
