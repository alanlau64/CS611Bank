import java.util.ArrayList;

public class StockController {
    private Stock stock;

    public StockController(){}

    public StockController(Stock stock){
        this.stock = stock;
    }

    public int increase(int amount){
        stock.setPrice(stock.getPrice() + amount);
        return stock.getPrice();
    }

    public int decrease(int amount){
        stock.setPrice(stock.getPrice() - amount);
        return stock.getPrice();
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
