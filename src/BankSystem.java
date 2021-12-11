import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BankSystem {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Loan> loansWaitToVerify = new ArrayList<>();
    private static LogFactory logFactory = new LogFactory();
    private static ArrayList<Stock> stocks = new ArrayList<>();


    public BankSystem(){}

    public void init(){
        //TODO: read files
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
        //TODO: write json to files
    }

    //click the button nextDay
    public static void nextDay(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Constant.CURRENT_TIME);
        calendar.add(Calendar.DATE,1);
        Constant.CURRENT_TIME = calendar.getTime();

        for(Customer customer: customers){
            for(Loan loan : customer.getLoans())
                //check whether the loan is overdue or not
                new CustomerController(customer).checkLoanOverdue(loan);
            for(SavingAccount savingAccount: customer.getSavings())
                //get the interest for saving account
                new SavingAccountController(savingAccount).getInterest();
        }
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
}
