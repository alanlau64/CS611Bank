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

    private JLabel amountLabel;
    private JLabel collateralLabel;
    private JTextField amount;
    private JTextField collateral;

    private JButton select;
    private JButton back;

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

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);
        amount = new JFormattedTextField(numberFormatter);

        collateral = new JTextField();

        amountLabel = new JLabel("Enter loan amount: ");
        collateralLabel = new JLabel("Enter your collateral: ");

        select = new JButton("Confirm");
        back = new JButton("Return");
    }

    public void showPage() {
        container.setLayout(null);

        usd.setBounds(50, 100, 150, 30);
        cny.setBounds(50, 150, 150, 30);
        inr.setBounds(50, 200, 150, 30);

        amountLabel.setBounds(50, 250, 150, 30);
        amount.setBounds(50, 300, 150, 30);
        collateralLabel.setBounds(50, 350, 150, 30);
        collateral.setBounds(50, 400, 150, 30);

        select.setBounds(50, 450, 150, 30);
        back.setBounds(50, 550, 150, 30);

        container.add(usd);
        container.add(cny);
        container.add(inr);
        container.add(amountLabel);
        container.add(amount);
        container.add(collateralLabel);
        container.add(collateral);
        container.add(select);
        container.add(back);

        select.addActionListener(this);
        back.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

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

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DATE, 10);
                Loan loan = customerController.requestLoan(currency, Double.parseDouble(amount.getText()), collateral.getText(), calendar.getTime());

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
