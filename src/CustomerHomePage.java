import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class CustomerHomePage extends JFrame implements ActionListener {
    private Container container;

    private JLabel checking;
    private JLabel saving;
    private JLabel securities;

    private JButton selectChecking;
    private JButton selectSaving;
    private JButton loans;
    private JButton openAccount;
    private JButton stocks;
    private JButton logout;

    private JComboBox<Integer> checkingAccounts;
    private JComboBox<Integer> savingsAccounts;

    private Customer customer;
    private CustomerController customerController;

    public CustomerHomePage(Customer customer) {
        this.customer = customer;
        this.customerController = new CustomerController(customer);
        container = getContentPane();
        checking = new JLabel("Checking Accounts: ");
        saving = new JLabel("Saving Accounts: ");

        String text;
        if(customer.getSecuritiesAccount() == null) {
            text = "None";
        } else {
            text = String.valueOf(customer.getSecuritiesAccount().getAccountNum());
        }
        securities = new JLabel("Seurities Account: " + text);
        openAccount = new JButton("Open an account");
        selectSaving = new JButton("Select account");
        selectChecking = new JButton("Select account");
        loans = new JButton("View loans");
        stocks = new JButton("View stocks");
        logout = new JButton("Logout");
        checkingAccounts = new JComboBox<Integer>(customerController.getCheckingAccountNums().toArray(Integer[]::new));
        savingsAccounts = new JComboBox<Integer>(customerController.getSavingAccountNums().toArray(Integer[]::new));
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        checking.setBounds(50, 50, 150, 30);
        checkingAccounts.setBounds(50, 100, 140, 20);
        selectChecking.setBounds(50, 150, 150, 30);

        saving.setBounds(50, 250, 150, 30);
        savingsAccounts.setBounds(50, 300, 140, 20);
        selectSaving.setBounds(50, 350, 150, 30);

        securities.setBounds(50, 400, 300, 30);

        loans.setBounds(50, 450, 150, 30);
        openAccount.setBounds(50, 500, 150, 30);
        stocks.setBounds(50, 550, 150, 30);
        logout.setBounds(50, 600, 150, 30);

        container.add(checking);
        container.add(checkingAccounts);
        container.add(selectChecking);
        container.add(saving);
        container.add(savingsAccounts);
        container.add(selectSaving);
        container.add(openAccount);
        container.add(loans);
        container.add(securities);
        container.add(stocks);
        container.add(logout);

        selectChecking.addActionListener(this);
        selectSaving.addActionListener(this);
        openAccount.addActionListener(this);
        loans.addActionListener(this);
        stocks.addActionListener(this);
        logout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openAccount) {
            OpenAccountPage frame = new OpenAccountPage(customer);
            frame.setTitle("Open new account");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == loans) {
            LoanPage frame = new LoanPage(customer);
            frame.setTitle("Loans");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == stocks) {
            if(customer.getSecuritiesAccount() == null) {
                JOptionPane.showMessageDialog(this, "Must have a securities account to view this page.");
            } else {
                CustomerStockView frame = new CustomerStockView(customer);
                frame.setTitle("Stocks");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 700);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);

                dispose();
                frame.showPage();
            }
        } else if (e.getSource() == logout) {
            LoginPage frame = new LoginPage();
            frame.setTitle("Login Page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else {

            if(e.getSource() == selectSaving) {
                SavingAccount account = customer.getSavings().get(savingsAccounts.getSelectedIndex());
                CheckingAndSavingAccountPage frame = new CheckingAndSavingAccountPage(account, customer);
                frame.setTitle("Account details");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 700);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);

                dispose();
                frame.showPage();
            } else {
                CheckingAccount account = customer.getCheckings().get(checkingAccounts.getSelectedIndex());
                CheckingAndSavingAccountPage frame = new CheckingAndSavingAccountPage(account, customer);
                frame.setTitle("Account details");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 700);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);

                dispose();
                frame.showPage();
            }
        }
    }
}
