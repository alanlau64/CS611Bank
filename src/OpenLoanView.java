import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class OpenLoanView extends JFrame implements ActionListener {

    private Container container;

    private Customer customer;
    private CustomerController customerController;

    private JRadioButton usd;
    private JRadioButton cny;
    private JRadioButton inr;
    private ButtonGroup group;

    private JRadioButton checking;
    private JRadioButton saving;
    private JRadioButton security;
    private ButtonGroup group2;

    private JLabel amountLabel;
    private JLabel daysLabel;
    private JLabel collateralLabel;
    private JFormattedTextField amount;
    private JFormattedTextField days;
    private JTextField collateral;

    private JButton select;
    private JButton back;
    private JComboBox<Integer> transferAccount;


    public OpenLoanView(Customer customer) {
        this.customer = customer;
        this.customerController = new CustomerController(customer);

        container = getContentPane();

        usd = new JRadioButton("USD");
        cny = new JRadioButton("CNY");
        inr = new JRadioButton("INR");
        group = new ButtonGroup();
        group.add(usd);
        group.add(cny);
        group.add(inr);

        checking = new JRadioButton("Checking");
        saving = new JRadioButton("Saving");
        security = new JRadioButton("Securities");
        group2 = new ButtonGroup();
        group2.add(checking);
        group2.add(saving);
        group2.add(security);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);
        amount = new JFormattedTextField(numberFormatter);
        days = new JFormattedTextField(numberFormatter);

        collateral = new JTextField();

        transferAccount = new JComboBox<Integer>(new Integer[20]);

        amountLabel = new JLabel("Enter loan amount: ");
        collateralLabel = new JLabel("Enter your collateral: ");
        daysLabel = new JLabel("Enter the loan period in days: ");

        select = new JButton("Confirm");
        back = new JButton("Return");
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        usd.setBounds(50, 50, 150, 30);
        cny.setBounds(50, 100, 150, 30);
        inr.setBounds(50, 150, 150, 30);

        amountLabel.setBounds(50, 200, 150, 30);
        amount.setBounds(50, 250, 150, 30);
        daysLabel.setBounds(50, 300, 150, 30);
        days.setBounds(50, 350, 150, 30);
        collateralLabel.setBounds(50, 400, 150, 30);
        collateral.setBounds(50, 450, 150, 30);

        checking.setBounds(50, 500, 150, 30);
        saving.setBounds(50, 550, 150, 30);
        security.setBounds(50, 600, 150, 30);
        transferAccount.setBounds(50, 650, 150, 30);

        select.setBounds(50, 700, 150, 30);
        back.setBounds(50, 750, 150, 30);
        this.setBounds(10, 10, 370, 850);

        container.add(daysLabel);
        container.add(days);
        container.add(usd);
        container.add(cny);
        container.add(inr);
        container.add(amountLabel);
        container.add(amount);
        container.add(collateralLabel);
        container.add(collateral);
        container.add(select);
        container.add(back);
        container.add(checking);
        container.add(saving);
        container.add(security);
        container.add(transferAccount);

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

        if(e.getSource() == select) {

            if(amount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                this.setVisible(false);
                this.setVisible(true);
            } else if (collateral.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter your collateral.");
                this.setVisible(false);
                this.setVisible(true);
            } else if (group.getSelection() == null){
                JOptionPane.showMessageDialog(this, "Please select a currency for your transaction.");
                this.setVisible(false);
                this.setVisible(true);
            } else {

                Currency currency = null;

                if(usd.isSelected()) {
                    currency = Currency.USD;
                } else if(cny.isSelected()) {
                    currency = Currency.CNY;
                } else if(inr.isSelected()){
                    currency = Currency.INR;
                }

                Account transferAccount;

                if(checking.isSelected()) {
                    transferAccount = customer.getCheckings().get(this.transferAccount.getSelectedIndex());
                } else if (saving.isSelected()) {
                    transferAccount = customer.getSavings().get(this.transferAccount.getSelectedIndex());
                } else {
                    transferAccount = customer.getSecuritiesAccount();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(Constant.CURRENT_TIME);
                calendar.add(Calendar.DATE, Integer.parseInt(days.getText()));
                Loan loan = customerController.requestLoan(currency, Double.parseDouble(amount.getText()), collateral.getText(), calendar.getTime(), transferAccount);

                if(loan != null) {
                    JOptionPane.showMessageDialog(this, "Successfully opened a loan!");
                } else {
                    JOptionPane.showMessageDialog(this, "Loan could not be created. ");
                }
            }
        } else if (e.getSource() == back) {
            LoanPage frame = new LoanPage(customer);
            frame.setTitle("Loans");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }

    }
}
