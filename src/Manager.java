import java.util.ArrayList;

public class Manager extends User{
    private StockController stockController;
    public Manager(){ }

    public Manager(String name, String password){
        super(name,password);
        BankSystem.addManager(this);
        stockController = new StockController();
    }

    public int increaseStockPrice(Stock stock, int amount){
        stockController.setStock(stock);
        return stockController.increase(amount);
    }

    public int decreaseStockPrice(Stock stock, int amount){
        stockController.setStock(stock);
        return stockController.decrease(amount);
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
        for(Customer customer : BankSystem.getCustomers()){
            if(customer.getSecuritiesAccount()!=null){
                if(customer.getSecuritiesAccount().getStocks().containsKey(newStock))
                    new SecuritiesAccountController(customer.getSecuritiesAccount()).sellStock(newStock,
                            customer.getSecuritiesAccount().getStocks().get(newStock));
            }
        }
        if(stocks.contains(newStock)){
            stocks.remove(newStock);
            return true;
        }
        else return false;
    }

    public void verifyLoan(Loan loan, boolean isVerify){
        loan.setVerify(true);
        BankSystem.addLoanActivity(new LoanActivity(Constant.CURRENT_TIME, loan, "approve"));
    }
}
