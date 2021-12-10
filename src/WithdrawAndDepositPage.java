import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class WithdrawAndDepositPage extends JFrame implements ActionListener {

    private Container container;

    private Customer customer;
    private Account account;

    private JRadioButton withdraw;
    private JRadioButton deposit;
    private ButtonGroup group;

    private JRadioButton usd;
    private JRadioButton cny;
    private JRadioButton inr;
    private ButtonGroup group2;

    private JFormattedTextField enterAmount;

    private JButton select;
    private JButton back;

    public WithdrawAndDepositPage(Account account, Customer customer) {
        this.account = account;
        this.customer = customer;

        container = getContentPane();

        withdraw = new JRadioButton("Withdraw Money");
        deposit = new JRadioButton("Deposit Money");
        group = new ButtonGroup();
        group.add(withdraw);
        group.add(deposit);

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

        select = new JButton("Select");
        back = new JButton("Return");
    }

    public void showPage() {
        container.setLayout(null);

        withdraw.setBounds(50, 150, 150, 30);
        deposit.setBounds(50, 200, 150, 30);
        enterAmount.setBounds(50, 250, 150, 30);
        usd.setBounds(50, 300, 60, 30);
        cny.setBounds(90, 300, 60, 30);
        inr.setBounds(130, 300, 60, 30);
        select.setBounds(50, 350, 150, 30);
        back.setBounds(50, 400, 150, 30);

        container.add(withdraw);
        container.add(deposit);
        container.add(select);
        container.add(back);
        container.add(cny);
        container.add(inr);
        container.add(usd);
        container.add(enterAmount);

        select.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == select) {

            Currency currency = null;
            if(usd.isSelected()) {
                currency = Currency.USD;
            } else if(cny.isSelected()) {
                currency = Currency.CNY;
            } else if(inr.isSelected()){
                currency = Currency.INR;
            } else {
                JOptionPane.showMessageDialog(this, "Please select a currency for your transaction.");
                return;
            }

            if(withdraw.isSelected()) {
                Double amount = account.withDraw(currency, Double.parseDouble(enterAmount.getText()));

                if(amount == null) {
                    JOptionPane.showMessageDialog(this,"Withdrawal failed. Check account balance or selected currency.");
                } else {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful. Current account balace: " + amount);
                }
            } else if (deposit.isSelected()) {
                Double amount =  account.deposit(currency, Double.parseDouble(enterAmount.getText()));

                if(amount == null) {
                    JOptionPane.showMessageDialog(this,"Deposit failed");
                } else {
                    JOptionPane.showMessageDialog(this, "Deposit successful. Current account balance: " + amount);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select either withdraw or deposit.");
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
