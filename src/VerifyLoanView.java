import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerifyLoanView extends JFrame implements ActionListener {

    private Manager manager;

    private Container container;

    private JLabel loansLabel;
    private JComboBox<Integer> loans;
    private JLabel loanUser;
    private JLabel loanAmount;
    private JLabel loanCurrency;
    private JLabel loanMortgage;
    private JLabel loanDue;
    private JButton verify;
    private JButton deny;
    private JButton back;

    public VerifyLoanView(Manager manager) {
        this.manager = manager;
        container = getContentPane();
        loansLabel = new JLabel("Loans to verify: ");
        loans = new JComboBox<Integer>(getLoansWaitToVerifyNums().toArray(Integer[]::new));
        loanAmount = new JLabel();
        loanCurrency = new JLabel();
        loanMortgage = new JLabel();
        loanUser = new JLabel();
        loanDue = new JLabel();
        verify = new JButton("Verify Loan");
        deny = new JButton("Deny Loan");
        back = new JButton("Back");
    }

    public void showPage() {
        container.setLayout(null);

        loansLabel.setBounds(50, 100, 150, 30);
        loans.setBounds(50, 150, 150, 30);
        loanUser.setBounds(50, 200, 150, 30);
        loanAmount.setBounds(50, 250, 150, 30);
        loanCurrency.setBounds(50, 300, 150, 30);
        loanMortgage.setBounds(50, 350, 150, 30);
        loanDue.setBounds(50, 400, 150, 30);
        verify.setBounds(50, 500, 150, 30);
        deny.setBounds(50, 550, 150, 30);
        back.setBounds(50, 600, 150, 30);

        container.add(loansLabel);
        container.add(loans);
        container.add(loanUser);
        container.add(loanAmount);
        container.add(loanCurrency);
        container.add(loanMortgage);
        container.add(loanDue);
        container.add(verify);
        container.add(deny);
        container.add(back);

        verify.addActionListener(this);
        deny.addActionListener(this);
        back.addActionListener(this);
        loans.addActionListener(this);
        this.addWindowListener(BankSystem.close());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loans) {
            Loan loan = new BankSystem().getLoansWaitToVerify().get(loans.getSelectedIndex());

            loanUser.setText(loan.getUserName());
            loanAmount.setText(String.valueOf(loan.getAmount()));
            loanCurrency.setText(String.valueOf(loan.getCurrency()));
            loanMortgage.setText(loan.getMortgage());
            loanDue.setText(String.valueOf(loan.getOverdueTime()));
        }

        if(e.getSource() == verify) {
            if(loans.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select a loan to verify");
            }

            manager.verifyLoan(new BankSystem().getLoansWaitToVerify().get(loans.getSelectedIndex()), true);
            JOptionPane.showMessageDialog(this, "Loan has been verified");
        }

        if(e.getSource() == deny) {
            if(loans.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select a loan to deny");
            }

            manager.verifyLoan(new BankSystem().getLoansWaitToVerify().get(loans.getSelectedIndex()), false);
            JOptionPane.showMessageDialog(this, "Loan has been denied");
        }

        if (e.getSource() == back) {
            ManagerHomePage frame = new ManagerHomePage(new Manager());
            frame.setTitle("Manager Home");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }

    public ArrayList<Integer> getLoansWaitToVerifyNums() {
        ArrayList<Integer> loanNums = new ArrayList<>();

        for(Loan loan: BankSystem.getLoansWaitToVerify()) {
            loanNums.add(loan.getLoanNum());
        }

        return loanNums;
    }
}
