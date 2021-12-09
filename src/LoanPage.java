import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanPage extends JFrame implements ActionListener {
    private Container container;

    private JLabel loansLabel;

    private JButton selectLoan;
    private JButton openLoan;
    private JButton back;

    private JComboBox<Integer> loans;

    private Customer customer;

    public LoanPage(Customer customer) {
        this.customer = customer;
        container = getContentPane();
        loansLabel = new JLabel("Active loans: ");
        openLoan = new JButton("Take a loan");
        selectLoan = new JButton("View loan");
        back = new JButton("Back");

        loans = new JComboBox<Integer>();
    }

    public void showPage() {
        container.setLayout(null);

        loansLabel.setBounds(50, 150, 150, 30);
        loans.setBounds(50, 220, 150, 20);
        selectLoan.setBounds(50, 290, 150, 30);


        openLoan.setBounds(50, 360, 150, 30);
        back.setBounds(50, 500, 150, 30);

        container.add(loansLabel);
        container.add(loans);
        container.add(selectLoan);
        container.add(openLoan);
        container.add(back);

        selectLoan.addActionListener(this);
        openLoan.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openLoan) {
            OpenAccountPage frame = new OpenAccountPage(customer);
            frame.setTitle("Open new account");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else {

        }
    }
}
