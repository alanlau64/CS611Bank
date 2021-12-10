import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class CustomerHomePage extends JFrame implements ActionListener {
    private Container container;

    private JLabel checking;
    private JLabel saving;

    private JButton selectChecking;
    private JButton selectSaving;
    private JButton loans;
    private JButton openAccount;

    private JComboBox<Integer> checkingAccounts;
    private JComboBox<Integer> savingsAccounts;

    private Customer customer;

    public CustomerHomePage(Customer customer) {
        this.customer = customer;
        container = getContentPane();
        checking = new JLabel("Checking Accounts: ");
        saving = new JLabel("Saving Accounts: ");
        openAccount = new JButton("Open an account");
        selectSaving = new JButton("Select account");
        selectChecking = new JButton("Select account");
        loans = new JButton("View loans");
        checkingAccounts = new JComboBox<Integer>(customer.getCheckingAccountNums().toArray(Integer[]::new));
        savingsAccounts = new JComboBox<Integer>(customer.getSavingAccountNums().toArray(Integer[]::new));
    }

    public void showPage() {
        container.setLayout(null);

        checking.setBounds(50, 150, 150, 30);
        checkingAccounts.setBounds(50, 220, 140, 20);
        selectChecking.setBounds(50, 290, 150, 30);

        saving.setBounds(50, 360, 150, 30);
        savingsAccounts.setBounds(50, 430, 140, 20);
        selectSaving.setBounds(50, 500, 150, 30);

        loans.setBounds(50, 550, 150, 30);
        openAccount.setBounds(50, 600, 150, 30);

        container.add(checking);
        container.add(checkingAccounts);
        container.add(selectChecking);
        container.add(saving);
        container.add(savingsAccounts);
        container.add(selectSaving);
        container.add(openAccount);
        container.add(loans);

        selectChecking.addActionListener(this);
        selectSaving.addActionListener(this);
        openAccount.addActionListener(this);
        loans.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openAccount) {
            OpenAccountPage frame = new OpenAccountPage(customer);
            frame.setTitle("Open new account");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == loans) {
            LoanPage frame = new LoanPage(customer);
            frame.setTitle("Loans");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else {
            Account account;

            if(e.getSource() == selectSaving) {
                account = customer.getSavings().get(savingsAccounts.getSelectedIndex());
            } else {
                account = customer.getCheckings().get(checkingAccounts.getSelectedIndex());
            }

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
