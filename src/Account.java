import java.util.HashMap;
import java.util.Map;

// the entity of all account
public class Account {
    private int accountNum;
    private Map<Currency, Double> balance;
    protected Withdraw withdrawTransaction;
    protected Deposit depositTransaction;
    protected Transfer transferTransaction;

    public Account(){
        accountNum = ++Constant.MAX_ACCOUNT_NUMBER;
        balance = new HashMap<>();
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    public Double withDraw(Currency currency, double money){
        return withdrawTransaction.withDraw(currency,money);
    }

    // return the current balance of given currency
    public Double deposit(Currency currency, double money){
        return depositTransaction.deposit(currency,money);
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
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
}
