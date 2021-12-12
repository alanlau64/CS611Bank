import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class BankSystem {
    private static ArrayList<Stock> stocks = new ArrayList<>();
    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Loan> loansWaitToVerify = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<StockTrade> stockTrades = new ArrayList<>();
    private static ArrayList<AccountActivity> accountActivities = new ArrayList<>();

    public static ArrayList<StockTrade> getStockTrades() {
        return stockTrades;
    }

    public static ArrayList<AccountActivity> getAccountActivities() {
        return accountActivities;
    }

    public static ArrayList<LoanActivity> getLoanActivities() {
        return loanActivities;
    }

    private static ArrayList<LoanActivity> loanActivities = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    private static LogFactory logFactory = new LogFactory();
    public BankSystem(){}

    public void init(){
        Constant.readConfig();
        Log customerLog = logFactory.getLog("customer");
        Log managerLog = logFactory.getLog("manager");
        Log transactionLog = logFactory.getLog("transaction");
        Log stockLog = logFactory.getLog("stock");
        Log stockTradeLog = logFactory.getLog("stockTrade");
        Log accountActivityLog = logFactory.getLog("accountActivity");
        Log loanActivityLog = logFactory.getLog("loanActivity");
        stocks = stockLog.readLog();
        customers = customerLog.readLog();
        managers = managerLog.readLog();
        transactions = transactionLog.readLog();
        stockTrades = stockTradeLog.readLog();
        accountActivities = accountActivityLog.readLog();
        loanActivities = loanActivityLog.readLog();
    }

    public void run(){
        init();
        LoginPage frame = new LoginPage();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        frame.showPage();
    }

    public static WindowAdapter close(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Constant.writeConfig();
                Log customerLog = logFactory.getLog("customer");
                Log managerLog = logFactory.getLog("manager");
                Log transactionLog = logFactory.getLog("transaction");
                Log stockLog = logFactory.getLog("stock");
                Log stockTradeLog = logFactory.getLog("stockTrade");
                Log accountActivityLog = logFactory.getLog("accountActivity");
                Log loanActivityLog = logFactory.getLog("loanActivity");
                customerLog.createLog(customers);
                managerLog.createLog(managers);
                transactionLog.createLog(transactions);
                stockLog.createLog(stocks);
                stockTradeLog.createLog(stockTrades);
                accountActivityLog.createLog(accountActivities);
                loanActivityLog.createLog(loanActivities);
            }};
    }

    // Click the button nextDay
    public static void nextDay(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Constant.CURRENT_TIME);
        calendar.add(Calendar.DATE,1);
        Constant.CURRENT_TIME = calendar.getTime();

        for(Customer customer: customers){
            if(customer.getLoans().size() != 0) {
                Iterator<Loan> iterator = customer.getLoans().iterator();
                while (iterator.hasNext()) {
                    // Check whether the loan is overdue or not
                    Loan loan = iterator.next();
                    if (new LoanController(loan).checkOverDue()) {
                        BankSystem.addLoanActivity(new LoanActivity(Constant.CURRENT_TIME, loan, "expire"));
                        iterator.remove();
                    }
                }
            }
            if(customer.getSavings().size() != 0) {
                for (SavingAccount savingAccount : customer.getSavings())
                    // Get the interest of saving accounts
                    new SavingAccountController(savingAccount).getInterest();
            }
        }
    }

    public static void addCustomer (Customer e) {
        customers.add(e);
    }

    public static void addManager (Manager e) {
        managers.add(e);
    }

    public static void addLoan (Loan e) {
        loansWaitToVerify.add(e);
    }

    public static void addStock (Stock e) {
        stocks.add(e);
    }

    public static void addTransaction (Transaction e) {
        transactions.add(e);
    }

    public static void addStockTrade (StockTrade e) {
        stockTrades.add(e);
    }

    public static void addAccountActivity (AccountActivity e) {
        accountActivities.add(e);
    }

    public static void addLoanActivity (LoanActivity e) {
        loanActivities.add(e);
    }

    public static void removeCustomer (Customer e) {
        customers.remove(e);
    }

    public static void removeManager (Manager e) {
        managers.remove(e);
    }

    public static void removeLoan (Loan e) {
        loansWaitToVerify.remove(e);
    }

    public static void removeStock (Stock e) {
        stocks.remove(e);
    }

    public static ArrayList<Stock> getAvailableStocks() {
        return stocks;
    }

    public static ArrayList<Loan> getLoansWaitToVerify() {
        return loansWaitToVerify;
    }

    public static ArrayList<Customer> getCustomers() { return customers; }

    public static ArrayList<Manager> getManagers() { return managers; }

    public static ArrayList<Transaction> getTransactions() { return transactions; }

    public static ArrayList<String> getAvailableStockNames() {
        ArrayList<String> stockNames = new ArrayList<>();

        for(Stock stock : stocks) {
            stockNames.add(stock.getName());
        }

        return stockNames;
    }
}
