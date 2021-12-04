import java.util.ArrayList;

public class BankManager extends User{
    public BankManager(){}

    public BankManager(String name, String password){
        super(name,password);
    }

    public int increaseStockPrice(Stock stock, int amount){
        return stock.increase(amount);
    }

    public int decreaseStockPrice(Stock stock, int amount){
        return stock.decrease(amount);
    }

    public boolean createStock(ArrayList<Stock> stocks, String name, int initPrice){
        Stock newStock = new Stock(name, initPrice);
        if(stocks.contains(newStock)){
            return false;
        }
        else {
            stocks.add(newStock);
            return true;
        }
    }

    public boolean deleteStock(ArrayList<Stock> stocks, String name){
        Stock newStock = new Stock(name);
        if(stocks.contains(newStock)){
            stocks.remove(newStock);
            return true;
        }
        else return false;
    }
}
