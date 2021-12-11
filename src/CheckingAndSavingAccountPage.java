import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckingAndSavingAccountPage extends JFrame implements ActionListener {

    private Account account;
    private Customer customer;
    private CustomerController customerController;

    private Container container;

    private JLabel accountNum;
    private JLabel currency;
    private JLabel usdAmount;
    private JLabel cnyAmount;
    private JLabel inrAmount;

    private JButton depositAndWithdraw;
    private JButton back;
    private JButton transfer;
    private JButton close;

    public CheckingAndSavingAccountPage(Account account, Customer customer) {
        container = getContentPane();

        this.account = account;
        this.customer = customer;
        this.customerController = new CustomerController(customer);

        accountNum = new JLabel(String.valueOf(account.getAccountNum()));

        usdAmount = new JLabel("USD: " + account.getBalanceWithCurrency(Currency.USD));
        cnyAmount = new JLabel("CNY: " + account.getBalanceWithCurrency(Currency.CNY));
        inrAmount = new JLabel("INR: " + account.getBalanceWithCurrency(Currency.INR));

        depositAndWithdraw = new JButton("Deposit/Withdraw Money");
        back = new JButton("Back");
        transfer = new JButton("Transfer Money");
        close = new JButton("Close account");
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        accountNum.setBounds(50, 50, 150, 30);
        usdAmount.setBounds(50, 100, 150, 30);
        cnyAmount.setBounds(50, 150, 150, 30);
        inrAmount.setBounds(50, 200, 150, 30);
        depositAndWithdraw.setBounds(50, 250, 150, 30);
        transfer.setBounds(50, 300, 150, 30);
        close.setBounds(50, 350, 150, 30);
        back.setBounds(50, 400, 150, 30);

        container.add(accountNum);
        container.add(usdAmount);
        container.add(cnyAmount);
        container.add(inrAmount);
        container.add(depositAndWithdraw);
        container.add(back);
        container.add(transfer);
        container.add(close);

        depositAndWithdraw.addActionListener(this);
        back.addActionListener(this);
        transfer.addActionListener(this);
        close.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == depositAndWithdraw) {
            WithdrawAndDepositPage frame = new WithdrawAndDepositPage(account, customer);
            frame.setTitle("Withdraw/Deposit");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if(e.getSource() == back) {
            CustomerHomePage frame = new CustomerHomePage(customer);
            frame.setTitle("Customer home page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == transfer) {
            TrasnferView frame = new TrasnferView(account, customer);
            frame.setTitle("Transfer money");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == close) {
            if(account.getClass().equals(CheckingAccount.class)) {
                if(customerController.closeCheckingAccount(account)) {
                    JOptionPane.showMessageDialog(this, "Account successfully closed");
                    CustomerHomePage frame = new CustomerHomePage(customer);
                    frame.setTitle("Customer home page");
                    frame.setVisible(true);
                    frame.setBounds(10, 10, 370, 700);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);

                    dispose();
                    frame.showPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Account cannot be closed. Please ensure there is no balance remaining.");
                }
            } else {
                if(customerController.closeSavingAccount(account)) {
                    JOptionPane.showMessageDialog(this, "Account successfully closed");
                    CustomerHomePage frame = new CustomerHomePage(customer);
                    frame.setTitle("Customer home page");
                    frame.setVisible(true);
                    frame.setBounds(10, 10, 370, 700);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);

                    dispose();
                    frame.showPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Account cannot be closed. Please ensure there is no balance remaining.");
                }
            }
        }
    }
}
