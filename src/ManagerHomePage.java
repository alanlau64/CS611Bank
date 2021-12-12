import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ManagerHomePage extends JFrame implements ActionListener {

    private Manager manager;

    private Container container;

    private JLabel bankStatistics;
    private JLabel numCustomers;
    private JLabel numAccounts;
    private JLabel totalLoansToVerify;
    private JLabel day;

    private JButton dailyReport;
    private JButton logout;
    private JButton viewStockPage;
    private JButton viewLoans;
    private JButton nextDay;
    private JButton refresh;

    public ManagerHomePage(Manager manager) {
        this.manager = manager;

        container = getContentPane();

        bankStatistics = new JLabel("Bank Statistics");
        numCustomers = new JLabel("Total number of customers: " + BankSystem.getCustomers().size());
        numAccounts = new JLabel("Total number of accounts: ");
        totalLoansToVerify = new JLabel("Loans to verify: " + BankSystem.getLoansWaitToVerify().size());

        nextDay = new JButton("Next day");
        dailyReport = new JButton("Create daily report");
        viewStockPage = new JButton("View stocks");
        logout = new JButton("Logout");
        viewLoans = new JButton("View loans");
        refresh = new JButton("Refresh");
        day = new JLabel(Constant.CURRENT_TIME.toString());
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        day.setBounds(0, 0, 200, 30);
        bankStatistics.setBounds(50, 100, 100, 30);
        numCustomers.setBounds(50, 150, 200, 30);
        numAccounts.setBounds(50, 200, 200, 30);
        totalLoansToVerify.setBounds(50, 250, 150, 30);
        dailyReport.setBounds(50, 300, 150, 30);
        viewStockPage.setBounds(50, 350, 150, 30);
        viewLoans.setBounds(50, 400, 150, 30);
        logout.setBounds(50, 500, 150, 30);
        nextDay.setBounds(210, 0, 150, 30);
        refresh.setBounds(210, 35, 100, 30);

        container.add(day);
        container.add(nextDay);
        container.add(bankStatistics);
        container.add(numCustomers);
        container.add(numAccounts);
        container.add(totalLoansToVerify);
        container.add(dailyReport);
        container.add(viewStockPage);
        container.add(logout);
        container.add(viewLoans);
        container.add(refresh);

        nextDay.addActionListener(this);
        dailyReport.addActionListener(this);
        viewStockPage.addActionListener(this);
        logout.addActionListener(this);
        viewLoans.addActionListener(this);
        refresh.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logout) {
            LoginPage frame = new LoginPage();
            frame.setTitle("Login Page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == viewStockPage) {
            ManagerStockView frame = new ManagerStockView(manager);
            frame.setTitle("Manager Stock View");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == viewLoans) {
            VerifyLoanView frame = new VerifyLoanView(manager);
            frame.setTitle("Loans");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == nextDay) {
            BankSystem.nextDay();
        } else if(e.getSource() == dailyReport) {
            DailyReportView frame = new DailyReportView(manager);
            frame.setTitle("Daily Report");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 900);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == refresh) {
            ManagerHomePage frame = new ManagerHomePage(manager);
            frame.setTitle(manager.getUsername() + "'s home page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
