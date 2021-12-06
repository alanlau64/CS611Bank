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

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    //TODO: add log
    public Double withDraw(Currency currency, double money){
        return withdrawTransaction.withDraw(currency,money);
    }

    // return the current balance of given currency
    //TODO: add log
    public Double deposit(Currency currency, double money){
        return depositTransaction.deposit(currency,money);
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    //TODO: add log
    public Double transfer(Account in, Currency currency, double money){
        return transferTransaction.transfer(in,currency,money);
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

    @Override
    public boolean equals(Object obj) {
        return this.accountNum == ((Account) obj).getAccountNum();
    }
}
