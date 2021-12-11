import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrasnferView extends JFrame implements ActionListener {

    private Container container;

    private Customer customer;
    private CustomerController customerController;
    private Account account;
    private AccountController accountController;

    private JLabel transferDestination;
    private JLabel transferAmountLabel;

    private JRadioButton usd;
    private JRadioButton cny;
    private JRadioButton inr;
    private ButtonGroup group2;

    private JFormattedTextField enterAmount;

    private JComboBox<Integer> checkingAccounts;
    private JComboBox<Integer> savingsAccounts;
    private JComboBox<Integer> securitiesAccount;

    private JButton securityTransfer;
    private JButton select;
    private JButton back;

    public TrasnferView(Account account, Customer customer) {
        this.account = account;
        this.customer = customer;
        this.customerController = new CustomerController(customer);
        this.accountController = new AccountController(account);

        container = getContentPane();

        transferDestination = new JLabel("Select transfer destination: ");
        transferAmountLabel = new JLabel("Enter amount to transfer: ");

        usd = new JRadioButton("USD");
        cny = new JRadioButton("CNY");
        inr = new JRadioButton("INR");
        group2 = new ButtonGroup();
        group2.add(usd);
        group2.add(cny);
        group2.add(inr);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);

        enterAmount = new JFormattedTextField(numberFormatter);
        ArrayList<Integer> checkingAccountNums = customerController.getCheckingAccountNums();
        checkingAccountNums.removeIf(n -> (n == account.getAccountNum()));
        ArrayList<Integer> savingAccountNums = customerController.getSavingAccountNums();
        savingAccountNums.removeIf(n -> (n == account.getAccountNum()));
        checkingAccounts = new JComboBox<Integer>(checkingAccountNums.toArray(Integer[]::new));
        savingsAccounts = new JComboBox<Integer>(savingAccountNums.toArray(Integer[]::new));

        if(customer.getSecuritiesAccount() == null) {
            securitiesAccount = new JComboBox<>(new Integer[5]);
        } else {
            securitiesAccount = new JComboBox<>(List.of(customer.getSecuritiesAccount().getAccountNum()).toArray(Integer[]::new));
        }

        securityTransfer = new JButton("Transfer to security account");
        select = new JButton("Confirm");
        back = new JButton("Back");
    }

    public void showPage() {
        container.setLayout(null);

        transferDestination.setBounds(50, 100, 150, 30);
        checkingAccounts.setBounds(50, 150, 150, 30);
        savingsAccounts.setBounds(50, 200, 150, 30);
        //securitiesAccount.setBounds(50, 250, 150, 30);
        transferAmountLabel.setBounds(50, 250, 150, 30);
        enterAmount.setBounds(50, 300, 150, 30);
        usd.setBounds(50, 350, 120, 30);
        cny.setBounds(90, 350, 120, 30);
        inr.setBounds(130, 350, 120, 30);
        select.setBounds(50, 400, 150, 30);
        back.setBounds(50, 550, 150, 30);
        securityTransfer.setBounds(50, 450, 200, 30);

        container.add(transferDestination);
        container.add(checkingAccounts);
        container.add(savingsAccounts);
        container.add(select);
        container.add(back);
        container.add(cny);
        container.add(inr);
        container.add(usd);
        container.add(enterAmount);
        //container.add(securitiesAccount);
        container.add(securityTransfer);
        container.add(transferAmountLabel);

        securityTransfer.addActionListener(this);
        select.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == select) {
            if (checkingAccounts.getSelectedIndex() == -1 && savingsAccounts.getSelectedIndex() == -1 && securitiesAccount.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select an account to transfer to.");
            } else if (enterAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            } else if (group2.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please select your transfer currency.");
            } else {
                Account transferAccount;

                if(checkingAccounts.getSelectedIndex() == -1) {
                    transferAccount = customer.getSavings().get(savingsAccounts.getSelectedIndex());
                } else {
                    transferAccount = customer.getCheckings().get(checkingAccounts.getSelectedIndex());
                }

                Currency currency = null;
                if(usd.isSelected()) {
                    currency = Currency.USD;
                } else if(cny.isSelected()) {
                    currency = Currency.CNY;
                } else if(inr.isSelected()){
                    currency = Currency.INR;
                }

                Double val = accountController.transfer(transferAccount, currency, Double.parseDouble(enterAmount.getText()));

                if(val == null) {
                    JOptionPane.showMessageDialog(this,"Transfer failed. Please check account balance.");
                } else {
                    JOptionPane.showMessageDialog(this, "Transfer successful! Remaining balance: " + val);
                }
            }
        } else if (e.getSource() == securityTransfer) {
            if (checkingAccounts.getSelectedIndex() == -1 && savingsAccounts.getSelectedIndex() == -1 && securitiesAccount.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select an account to transfer to.");
            } else if (enterAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            } else if (group2.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please select your transfer currency.");
            } else if (customer.getSecuritiesAccount() == null){
                JOptionPane.showMessageDialog(this, "No securities account. Please open a securities account.");
            } else if (account.getAccountNum() == customer.getSecuritiesAccount().getAccountNum()){
                JOptionPane.showMessageDialog(this, "Cannot transfer to the same account.");
            } else {
                Account transferAccount = customer.getSecuritiesAccount();

                Currency currency = null;
                if(usd.isSelected()) {
                    currency = Currency.USD;
                } else if(cny.isSelected()) {
                    currency = Currency.CNY;
                } else if(inr.isSelected()){
                    currency = Currency.INR;
                }

                Double val = accountController.transfer(transferAccount, currency, Double.parseDouble(enterAmount.getText()));

                if(val == null) {
                    JOptionPane.showMessageDialog(this,"Transfer failed. Please check account balance.");
                } else {
                    JOptionPane.showMessageDialog(this, "Transfer successful! Remaining balance: " + val);
                }
            }
        } else if (e.getSource() == back) {
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
