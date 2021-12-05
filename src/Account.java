import java.util.HashMap;
import java.util.Map;

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

    public Double withDraw(Currency currency, double money){
        return withdrawTransaction.withDraw(currency,money);
    }

    public Double deposit(Currency currency, double money){
        return depositTransaction.deposit(currency,money);
    }

    public Double transfer(Account in, Currency currency, double money){
        return transferTransaction.transfer(in,currency,money);
    }

    public Double getBalanceWithCurrency(Currency currency){
        return balance.get(currency);
    }

    public Map<Currency, Double> getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNum;
    }
}
