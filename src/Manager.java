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
        } else {
            stocks.add(newStock);
            return true;
        }
    }

    public boolean deleteStock(ArrayList<Stock> stocks, Stock stock){
        for(Customer customer : BankSystem.getCustomers()){
            if(customer.getSecuritiesAccount()!=null){
                if(customer.getSecuritiesAccount().getStocks().containsKey(stock))
                    new SecuritiesAccountController(customer.getSecuritiesAccount()).sellStock(stock,
                            customer.getSecuritiesAccount().getStocks().get(stock));
            }
        }

        if(stocks.contains(stock)){
            stocks.remove(stock);
            return true;
        }
        else return false;
    }

    public void verifyLoan(Loan loan, boolean isApprove){
        loan.setVerify(true);
        if(isApprove) {
            BankSystem.addLoanActivity(new LoanActivity(Constant.CURRENT_TIME, loan, "approve"));
            new AccountController(loan.getInAccount()).deposit(loan.getCurrency(), loan.getAmount());
            new LoanController(loan).ChargeInterest();
        }
        else {
            BankSystem.addLoanActivity(new LoanActivity(Constant.CURRENT_TIME, loan, "deny"));
            for(Customer customer : BankSystem.getCustomers()){
                if(customer.getUsername().equalsIgnoreCase(loan.getUserName()))
                    customer.getLoans().remove(loan);
            }
        }
        BankSystem.getLoansWaitToVerify().remove(loan);
    }
}
