import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAccountPage extends JFrame implements ActionListener {
    private Container container;

    private Customer customer;
    private CustomerController customerController;

    private JRadioButton checking;
    private JRadioButton saving;
    private JRadioButton security;
    private ButtonGroup group;

    private JButton select;
    private JButton back;

    public OpenAccountPage(Customer customer) {
        this.customer = customer;
        this.customerController = new CustomerController(customer);

        container = getContentPane();
        checking = new JRadioButton("Checking Account");
        saving = new JRadioButton("Savings Account");
        security = new JRadioButton("Securities Account");
        group = new ButtonGroup();
        group.add(checking);
        group.add(saving);
        group.add(security);
        select = new JButton("Select");
        back = new JButton("Return");
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        checking.setBounds(50, 150, 150, 30);
        saving.setBounds(50, 200, 150, 30);
        security.setBounds(50, 250, 150, 30);
        select.setBounds(50, 300, 150, 30);
        back.setBounds(50, 350, 150, 30);

        container.add(checking);
        container.add(saving);
        container.add(security);
        container.add(select);
        container.add(back);

        select.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == select) {
            if(checking.isSelected()) {
                customerController.openCheckingAccount();
                JOptionPane.showMessageDialog(this, "Successfully opened a checking account.");
            } else if (saving.isSelected()) {
                customerController.openSavingAccount();
                JOptionPane.showMessageDialog(this, "Successfully opened a savings account.");
            } else if(security.isSelected()) {
                if(customerController.openSecuritiesAccount()) {
                    JOptionPane.showMessageDialog(this, "Successfully opened a securities account.");
                } else {
                    JOptionPane.showMessageDialog(this, "Opening a securities account failed. Account balance is too low.");
                }
            }
        } else if(e.getSource() == back) {
            CustomerHomePage frame = new CustomerHomePage(customer);
            frame.setTitle("Open new account");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
