import java.util.Map;

public class SecuritiesAccountController extends AccountController{
    public SecuritiesAccountController(){}

    public SecuritiesAccountController(SecuritiesAccount securitiesAccount){
        setAccount(securitiesAccount);
    }

    //TODO: add log
    public boolean buyStock(Stock stock, int amount){
        Map<Stock, Integer> stocks = ((SecuritiesAccount)account).getStocks();
        if(account.getBalance().get(stock.getCurrency()) >= amount * stock.getPrice()){
            account.getBalance().put(stock.getCurrency(), account.getBalance().get(stock.getCurrency())
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
        Map<Stock, Integer> stocks = ((SecuritiesAccount)account).getStocks();
        if(stocks.containsKey(stock) && stocks.get(stock) >= amount){
            stocks.put(stock, stocks.get(stock) - amount);
            account.getBalance().put(stock.getCurrency(), account.getBalance().get(stock.getCurrency())
                    + amount * stock.getPrice());
            if(stocks.get(stock) == 0)
                stocks.remove(stock);
            return true;
        }
        // don't have enough amount of stock to sell
        else return false;
    }
}
