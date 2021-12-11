import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class CreateAccountPage extends JFrame implements ActionListener {
    private Container container;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField secretPasswordField;

    private JLabel username;
    private JLabel password;
    private JLabel secret;

    private JButton create;

    public CreateAccountPage() {
        container = getContentPane();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        secretPasswordField = new JPasswordField();
        username = new JLabel("Username");
        password = new JLabel("Password");
        secret = new JLabel("Secret");
        create = new JButton("Create Account");
        //all page have to contain this close object
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        secretPasswordField.setBounds(150, 290, 150, 30);
        username.setBounds(50, 150, 100, 30);
        password.setBounds(50, 220, 100, 30);
        secret.setBounds(50, 290, 150, 30);
        create.setBounds(50, 360, 150, 30);

        container.add(username);
        container.add(password);
        container.add(usernameField);
        container.add(passwordField);
        container.add(create);
        container.add(secret);
        container.add(secretPasswordField);

        create.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create) {

            if(Arrays.toString(secretPasswordField.getPassword()).equals("cpk")) {
                Manager manager = new Manager(usernameField.getText(), Arrays.toString(passwordField.getPassword()));
                BankSystem.addManager(manager);
            } else {
                Customer customer = new Customer(usernameField.getText(), Arrays.toString(passwordField.getPassword()));
                BankSystem.addCustomer(customer);

                for(Customer customer1 : BankSystem.getCustomers()) {
                    System.out.println(customer1.getUsername());
                }
            }

            LoginPage frame = new LoginPage();
            frame.setTitle("Login Form");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
