import java.util.ArrayList;

public class Manager extends User{
    private StockController stockController;
    public Manager(){
        stockController = new StockController();
    }

    public Manager(String name, String password){
        super(name,password);
        BankSystem.addManager(this);
    }

    //TODO: add log
    public int increaseStockPrice(Stock stock, int amount){
        stockController.setStock(stock);
        return stockController.increase(amount);
    }

    //TODO: add log
    public int decreaseStockPrice(Stock stock, int amount){
        stockController.setStock(stock);
        return stockController.decrease(amount);
    }

    //TODO: add log
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

    //TODO: add log
    public boolean deleteStock(ArrayList<Stock> stocks, String name){
        Stock newStock = new Stock(name);
        if(stocks.contains(newStock)){
            stocks.remove(newStock);
            return true;
        }
        else return false;
    }

    //TODO: add log
    public void verifyLoan(Loan loan, boolean isVerify){
        loan.setVerify(true);
    }
}
