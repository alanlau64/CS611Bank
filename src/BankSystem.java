import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BankSystem {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Loan> loansWaitToVerify = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<Stock> stocks = new ArrayList<>();

    private static LogFactory logFactory = new LogFactory();
    public BankSystem(){}

    public void init(){
        Constant.readConfig();
        Log customerLog = logFactory.getLog("customer");
        Log managerLog = logFactory.getLog("manager");
        Log transactionLog = logFactory.getLog("transaction");
        Log stockLog = logFactory.getLog("stock");
        customerLog.readLog();
        managerLog.readLog();
        transactionLog.readLog();
        stockLog.readLog();
    }

    public void run(){
        init();

        //TODO: the logic of bank system
        LoginPage frame = new LoginPage();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.showPage();

        close();
    }

    public void close(){
        Constant.writeConfig();
        Log customerLog = logFactory.getLog("customer");
        Log managerLog = logFactory.getLog("manager");
        Log transactionLog = logFactory.getLog("transaction");
        Log stockLog = logFactory.getLog("stock");
        customerLog.createLog(customers);
        managerLog.createLog(managers);
        transactionLog.createLog(transactions);
        stockLog.createLog(stocks);
    }

    // Click the button nextDay
    public static void nextDay(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Constant.CURRENT_TIME);
        calendar.add(Calendar.DATE,1);
        Constant.CURRENT_TIME = calendar.getTime();

        for(Customer customer: customers){
            for(Loan loan : customer.getLoans())
                // Check whether the loan is overdue or not
                new CustomerController(customer).checkLoanOverdue(loan);
            for(SavingAccount savingAccount: customer.getSavings())
                // Get the interest of saving accounts
                new SavingAccountController(savingAccount).getInterest();
        }
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
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
        Stock stock = new Stock("MSF", 300);
        stocks.add(stock);
        return stocks;
    }

    public static ArrayList<Loan> getLoansWaitToVerify() {
        return loansWaitToVerify;
    }
}
