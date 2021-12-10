import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class TrasnferView extends JFrame implements ActionListener {

    private Container container;

    private Customer customer;
    private Account account;

    private JLabel transferDestination;

    private JRadioButton usd;
    private JRadioButton cny;
    private JRadioButton inr;
    private ButtonGroup group2;

    private JFormattedTextField enterAmount;

    private JComboBox<Integer> checkingAccounts;
    private JComboBox<Integer> savingsAccounts;


    private JButton select;
    private JButton back;

    public TrasnferView(Account account, Customer customer) {
        this.account = account;
        this.customer = customer;

        container = getContentPane();

        transferDestination = new JLabel("Select transfer destination: ");

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
        checkingAccounts = new JComboBox<Integer>(customer.getCheckingAccountNums().toArray(Integer[]::new));
        savingsAccounts = new JComboBox<Integer>(customer.getSavingAccountNums().toArray(Integer[]::new));


        select = new JButton("Confirm");
        back = new JButton("Back");
    }

    public void showPage() {
        container.setLayout(null);

        transferDestination.setBounds(50, 100, 150, 30);
        checkingAccounts.setBounds(50, 150, 150, 30);
        savingsAccounts.setBounds(50, 200, 150, 30);
        enterAmount.setBounds(50, 250, 150, 30);
        usd.setBounds(50, 300, 60, 30);
        cny.setBounds(90, 300, 60, 30);
        inr.setBounds(130, 300, 60, 30);
        select.setBounds(50, 350, 150, 30);
        back.setBounds(50, 400, 150, 30);

        container.add(transferDestination);
        container.add(checkingAccounts);
        container.add(savingsAccounts);
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
        if (e.getSource() == select) {
            if (checkingAccounts.getSelectedIndex() == -1 && savingsAccounts.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select an account to transfer to.");
            } else if (enterAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            } else if (group2.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please select your transfer currency.");
            } else {
                Account transferAccount;

                if(checkingAccounts.getSelectedIndex() ==  -1) {
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

                Double val = account.transfer(transferAccount, currency, Double.parseDouble(enterAmount.getText()));

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
