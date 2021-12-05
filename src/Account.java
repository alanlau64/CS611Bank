import java.util.Map;

public class Account {
    private int accountNum;
    private Map<Currency, Double> balance;

    public Account(){
        accountNum = ++Constant.MAX_ACCOUNT_NUMBER;
    }

    public Double withDraw(Currency currency, double money){
        if(balance.containsKey(currency) && balance.get(currency) >= money) {
            balance.put(currency, balance.get(currency) - money);
            return balance.get(currency);
        }
        else return -1.0;
    }

    public Double deposit(Currency currency, double money){
        if(balance.containsKey(currency))
            balance.put(currency, balance.get(currency) + money);
        else
            balance.put(currency, + money);
        return balance.get(currency);
    }

    public Double transfer(Account in, Currency currency, double money){
        if(this.withDraw(currency,money) >= 0){
            in.deposit(currency, money);
            return getBalanceWithCurrency(currency);
        }
        else
            return -1.0;
    }

    public Double getBalanceWithCurrency(Currency currency){
        return balance.get(currency);
    }

    public int getAccountNum() {
        return accountNum;
    }
}
