import java.util.HashMap;
import java.util.Map;

public class SecuritiesAccount extends Account{
    private Map<Stock, Integer> stocks;

    public SecuritiesAccount(){
        super();
        withdrawTransaction = new WithdrawWithTransactionFee(this);
        depositTransaction = new DepositWithoutTransactionFee(this);
        transferTransaction = new TransferWithoutTransactionFee(this);
        stocks = new HashMap<>();
    }
}
