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

    private JTextField payAmount;
    private JButton pay;
    private JButton back;

    private Customer customer;
    private Loan loan;

    public SingleLoanView(Customer customer, Loan loan) {
        container = getContentPane();

        this.customer = customer;
        this.loan = loan;

        header = new JLabel("Loan Information");
        currency = new JLabel("Currency: " + loan.getCurrency());
        amount = new JLabel("Amount: " + loan.getAmount());
        interest = new JLabel("Interest: " + Constant.LOAN_INTEREST);
        back = new JButton("Back");

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);
        payAmount = new JFormattedTextField(numberFormatter);

        pay = new JButton("Pay Loan");
    }

    public void showPage() {
        container.setLayout(null);

        header.setBounds(50, 100, 150, 50);
        currency.setBounds(50, 150, 150, 50);
        amount.setBounds(50, 200, 150, 50);
        interest.setBounds(50, 250, 150, 50);

        payAmount.setBounds(50, 350, 150, 30);
        pay.setBounds(50, 400, 150, 30);
        back.setBounds(50, 500, 100, 30);

        container.add(header);
        container.add(currency);
        container.add(amount);
        container.add(interest);
        container.add(payAmount);
        container.add(pay);
        container.add(back);

        back.addActionListener(this);
        pay.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pay) {

            if(payAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                this.setVisible(false);
                this.setVisible(true);
            }

            double val = loan.payBack((double) Integer.parseInt(payAmount.getText()));

            if(val == -1.0) {
                JOptionPane.showMessageDialog(this, "Not enough money in account to pay this amount. Please deposit money or pay a lower amount.");
            } else {
                JOptionPane.showMessageDialog(this, "Payment successful. Amount left in loan: " + val);
                this.setVisible(false);
                this.setVisible(true);
            }
        } else if (e.getSource() == back) {
            LoanPage frame = new LoanPage(customer);
            frame.setTitle("Loans");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            frame.showPage();
        }
    }
}
