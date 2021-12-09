import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerHomePage extends JFrame implements ActionListener {

    private Manager manager;

    private Container container;

    private JLabel bankStatistics;
    private JLabel numCustomers;
    private JLabel numAccounts;
    private JLabel totalMoneyInBank;

    private JButton dailyReport;
    private JButton logout;
    private JButton viewStockPage;

    public ManagerHomePage(Manager manager) {
        this.manager = manager;

        container = getContentPane();

        bankStatistics = new JLabel("Bank Statistics");
        numCustomers = new JLabel("Total number of customers: ");
        numAccounts = new JLabel("Total number of accounts: ");
        totalMoneyInBank = new JLabel("Total money in bank: ");

        dailyReport = new JButton("Create daily report");
        viewStockPage = new JButton("View stocks");
        logout = new JButton("Logout");
    }

    public void showPage() {
        container.setLayout(null);

        bankStatistics.setBounds(50, 100, 100, 100);
        numCustomers.setBounds(50, 210, 200, 30);
        numAccounts.setBounds(50, 260, 200, 30);
        totalMoneyInBank.setBounds(50, 310, 150, 30);
        dailyReport.setBounds(50, 360, 150, 30);
        viewStockPage.setBounds(50, 410, 150, 30);
        logout.setBounds(50, 460, 150, 30);

        container.add(bankStatistics);
        container.add(numCustomers);
        container.add(numAccounts);
        container.add(totalMoneyInBank);
        container.add(dailyReport);
        container.add(viewStockPage);
        container.add(logout);

        dailyReport.addActionListener(this);
        viewStockPage.addActionListener(this);
        logout.addActionListener(this);
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
        }
    }
}
