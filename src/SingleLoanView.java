import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Objects;

public class SingleLoanView extends JFrame implements ActionListener {

    private Container container;

    private JLabel header;
    private JLabel currency;
    private JLabel amount;
    private JLabel interest;
    private JLabel dueTime;

    private JTextField payAmount;
    private JButton pay;
    private JButton pay1;
    private JButton back;

    private JRadioButton checking;
    private JRadioButton saving;
    private JRadioButton security;
    private ButtonGroup group;

    private JComboBox<Integer> checkingAccounts;
    private JComboBox<Integer> savingsAccounts;

    private Customer customer;
    private CustomerController customerController;
    private Loan loan;
    private LoanController loanController;

    public SingleLoanView(Customer customer, Loan loan) {
        container = getContentPane();

        this.customer = customer;
        this.customerController = new CustomerController(customer);
        this.loan = loan;
        this.loanController = new LoanController(loan);

        header = new JLabel("Loan Information");
        currency = new JLabel("Currency: " + loan.getCurrency());
        amount = new JLabel("Amount: " + loan.getAmount());
        interest = new JLabel("Interest: " + Constant.LOAN_INTEREST);
        dueTime = new JLabel("Due Time: " + loan.getOverdueTime().toString());
        back = new JButton("Back");

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);
        payAmount = new JFormattedTextField(numberFormatter);

        pay = new JButton("Pay Loan");
        pay1 = new JButton("Pay Loan");

        checking = new JRadioButton("Checking");
        saving = new JRadioButton("Saving");
        group = new ButtonGroup();
        group.add(checking);
        group.add(saving);

        checkingAccounts = new JComboBox<Integer>(new Integer[20]);
        //savingsAccounts = new JComboBox<Integer>(customerController.getSavingAccountNums().toArray(Integer[]::new));
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        header.setBounds(50, 100, 150, 50);
        currency.setBounds(50, 150, 150, 50);
        amount.setBounds(50, 200, 150, 50);
        interest.setBounds(50, 250, 150, 50);
        dueTime.setBounds(50, 300, 250, 30);

        payAmount.setBounds(50, 350, 150, 30);
        checking.setBounds(50, 400, 150, 30);
        saving.setBounds(50, 450, 150, 30);
        checkingAccounts.setBounds(50, 500, 150, 30);
        //pay.setBounds(50, 450, 150, 30);

        //savingsAccounts.setBounds(50, 500, 150, 30);
        pay1.setBounds(50, 550, 150, 30);
        back.setBounds(50, 600, 100, 30);

        container.add(dueTime);
        container.add(header);
        container.add(currency);
        container.add(amount);
        container.add(interest);
        container.add(payAmount);
        //container.add(pay);
        container.add(back);
        //container.add(savingsAccounts);
        container.add(checkingAccounts);
        container.add(pay1);
        container.add(checking);
        container.add(saving);

        checking.addActionListener(this);
        saving.addActionListener(this);
        back.addActionListener(this);
        pay.addActionListener(this);
        pay1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == checking) {
            checkingAccounts.removeAllItems();
            for(Integer i : customerController.getCheckingAccountNums()) {
                checkingAccounts.addItem(i);
            }

            //checkingAccounts = new JComboBox<>(customerController.getCheckingAccountNums().toArray(Integer[]::new));
        } else if (e.getSource() == saving) {
            checkingAccounts.removeAllItems();
            for(Integer i : customerController.getSavingAccountNums()) {
                checkingAccounts.addItem(i);
            }
        }

        if (e.getSource() == back) {
            LoanPage frame = new LoanPage(customer);
            frame.setTitle("Loans");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == pay1){
            Account account = null;

            if(checking.isSelected()) {
                account = customer.getCheckings().get(this.checkingAccounts.getSelectedIndex());
            } else if (saving.isSelected()) {
                account = customer.getSavings().get(this.checkingAccounts.getSelectedIndex());
            }

            if(payAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                this.setVisible(false);
                this.setVisible(true);
            }

            System.out.println(loan.getIsVerify());
            Double val = customerController.payBackLoan(account, Double.parseDouble(payAmount.getText()), loan);

            if(val == null) {
                JOptionPane.showMessageDialog(this, "Loan may not be verified or not enough balance. Please check your balance or contact the manager to verify the loan.");
            } else {
                JOptionPane.showMessageDialog(this, "Payment successful. Amount left in loan: " + val);
                this.setVisible(false);
                this.setVisible(true);
            }
        }
    }
}
