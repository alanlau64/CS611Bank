import java.util.HashMap;
import java.util.Map;

public class SecuritiesAccount extends Account{
    private Map<Stock, Integer> stocks;

    public SecuritiesAccount(){}

    public SecuritiesAccount(String userName){
        super(userName);
        withdrawTransaction = new WithdrawWithTransactionFee(this);
        depositTransaction = new DepositWithoutTransactionFee(this);
        transferTransaction = new TransferWithoutTransactionFee(this);
        stocks = new HashMap<>();
    }

    //TODO: add log
    public boolean buyStock(Stock stock, int amount){
        if(this.getBalance().get(stock.getCurrency()) >= amount * stock.getPrice()){
            this.getBalance().put(stock.getCurrency(), this.getBalance().get(stock.getCurrency())
                    - amount * stock.getPrice());
            if(stocks.containsKey(stock))
                stocks.put(stock, stocks.get(stock) + amount);
            else stocks.put(stock, amount);
            return true;
        }
        // don't have enough money
        else return false;
    }

    //TODO: add log
    public boolean sellStock(Stock stock, int amount){
        if(stocks.containsKey(stock) && stocks.get(stock) >= amount){
            stocks.put(stock, stocks.get(stock) - amount);
            this.getBalance().put(stock.getCurrency(), this.getBalance().get(stock.getCurrency())
                    + amount * stock.getPrice());
            return true;
        }
        // don't have enough amount of stock to sell
        else return false;
    }

    public Map<Stock, Integer> getStocks() {
        return stocks;
    }
}
