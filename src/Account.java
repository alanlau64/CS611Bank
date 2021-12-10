import java.util.HashMap;
import java.util.Map;

// the entity of all account
public class Account {
    private int accountNum;
    private Map<Currency, Double> balance;
    protected Withdraw withdrawTransaction;
    protected Deposit depositTransaction;
    protected Transfer transferTransaction;
    private String userName;

    public Account(){}

    public Account(String userName){
        accountNum = ++Constant.MAX_ACCOUNT_NUMBER;
        balance = new HashMap<>();
        this.userName = userName;
    }

    public Double getBalanceWithCurrency(Currency currency){
        return balance.getOrDefault(currency, null);
    }

    public Map<Currency, Double> getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public Withdraw getWithdrawTransaction() {
        return withdrawTransaction;
    }

    public Deposit getDepositTransaction() {
        return depositTransaction;
    }

    public Transfer getTransferTransaction() {
        return transferTransaction;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object obj) {
        return this.accountNum == ((Account) obj).getAccountNum();
    }
}
