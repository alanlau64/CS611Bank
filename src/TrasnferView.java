import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class TrasnferView extends JFrame implements ActionListener {

    private Container container;

    private Customer customer;
    private CustomerController customerController;
    private Account account;
    private AccountController accountController;

    private JLabel transferDestination;

    private JRadioButton usd;
    private JRadioButton cny;
    private JRadioButton inr;
    private ButtonGroup group2;

    private JRadioButton checking;
    private JRadioButton saving;
    private JRadioButton security;
    private ButtonGroup group;

    private JFormattedTextField enterAmount;

    private JComboBox<Integer> transferAccount;
    //private JComboBox<Integer> savingsAccounts;
    //private JComboBox<Integer> securitiesAccount;


    private JButton select;
    private JButton back;

    public TrasnferView(Account account, Customer customer) {
        this.account = account;
        this.customer = customer;
        this.customerController = new CustomerController(customer);
        this.accountController = new AccountController(account);

        container = getContentPane();

        transferDestination = new JLabel("Select transfer destination: ");

        usd = new JRadioButton("USD");
        cny = new JRadioButton("CNY");
        inr = new JRadioButton("INR");
        group2 = new ButtonGroup();
        group2.add(usd);
        group2.add(cny);
        group2.add(inr);

        checking = new JRadioButton("Checking");
        saving = new JRadioButton("Saving");
        security = new JRadioButton("Securities");
        group = new ButtonGroup();
        group.add(checking);
        group.add(saving);
        group.add(security);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);

        enterAmount = new JFormattedTextField(numberFormatter);
        //ArrayList<Integer> checkingAccountNums = customerController.getCheckingAccountNums();
        //checkingAccountNums.removeIf(n -> (n == account.getAccountNum()));
        //ArrayList<Integer> savingAccountNums = customerController.getSavingAccountNums();
        //savingAccountNums.removeIf(n -> (n == account.getAccountNum()));
        transferAccount = new JComboBox<Integer>(new Integer[20]);
        //savingsAccounts = new JComboBox<Integer>(savingAccountNums.toArray(Integer[]::new));

        //if(customer.getSecuritiesAccount() == null) {
            //securitiesAccount = new JComboBox<>(new Integer[5]);
        //} else {
            //securitiesAccount = new JComboBox<>(List.of(customer.getSecuritiesAccount().getAccountNum()).toArray(Integer[]::new));
        //}


        select = new JButton("Confirm");
        back = new JButton("Back");
    }

    public void showPage() {
        container.setLayout(null);

        transferDestination.setBounds(50, 50, 150, 30);
        checking.setBounds(50, 100, 150, 30);
        saving.setBounds(50, 150, 150, 30);
        security.setBounds(50, 200, 150, 30);
        transferAccount.setBounds(50, 250, 150, 30);
        //savingsAccounts.setBounds(50, 200, 150, 30);
        //securitiesAccount.setBounds(50, 250, 150, 30);
        enterAmount.setBounds(50, 300, 150, 30);
        usd.setBounds(50, 350, 100, 30);
        cny.setBounds(90, 350, 100, 30);
        inr.setBounds(130, 350, 100, 30);
        select.setBounds(50, 400, 150, 30);
        back.setBounds(50, 450, 150, 30);

        container.add(transferDestination);
        container.add(transferAccount);
        //container.add(savingsAccounts);
        container.add(select);
        container.add(back);
        container.add(cny);
        container.add(inr);
        container.add(usd);
        container.add(enterAmount);
        //ontainer.add(securitiesAccount);
        container.add(checking);
        container.add(saving);
        container.add(security);

        checking.addActionListener(this);
        saving.addActionListener(this);
        security.addActionListener(this);
        select.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checking) {
            transferAccount.removeAllItems();
            for(Integer i : customerController.getCheckingAccountNums()) {
                transferAccount.addItem(i);
            }

            //checkingAccounts = new JComboBox<>(customerController.getCheckingAccountNums().toArray(Integer[]::new));
        } else if (e.getSource() == saving) {
            transferAccount.removeAllItems();
            for(Integer i : customerController.getSavingAccountNums()) {
                transferAccount.addItem(i);
            }
        } else if (e.getSource() == security) {
            transferAccount.removeAllItems();

            if(customer.getSecuritiesAccount() == null) {
                JOptionPane.showMessageDialog(this, "No securities account present");
            } else {
                transferAccount.addItem(customer.getSecuritiesAccount().getAccountNum());
            }
        }

        if (e.getSource() == select) {
            if (transferAccount.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select an account to transfer to.");
            } else if (enterAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            } else if (group2.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please select your transfer currency.");
            } else {
                Account transferAccount;

                if(checking.isSelected()) {
                    transferAccount = customer.getCheckings().get(this.transferAccount.getSelectedIndex());
                } else if (saving.isSelected()) {
                    transferAccount = customer.getSavings().get(this.transferAccount.getSelectedIndex());
                } else {
                    transferAccount = customer.getSecuritiesAccount();
                }

                /***
                if(checkingAccounts.getSelectedIndex() !=  -1) {
                    transferAccount = customer.getCheckings().get(checkingAccounts.getSelectedIndex());
                } else {
                    transferAccount = customer.getSavings().get(savingsAccounts.getSelectedIndex());
                }
                 ***/

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
