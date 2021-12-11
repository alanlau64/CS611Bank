import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class CreateUserPage extends JFrame implements ActionListener {
    private Container container;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField secretPasswordField;

    private JLabel username;
    private JLabel password;
    private JLabel secret;

    private JButton create;
    private JButton back;

    public CreateUserPage() {
        container = getContentPane();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        secretPasswordField = new JPasswordField();
        username = new JLabel("Username");
        password = new JLabel("Password");
        secret = new JLabel("Secret");
        create = new JButton("Create Account");
        back = new JButton("Back");
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
        back.setBounds(50, 430, 150, 30);

        container.add(username);
        container.add(password);
        container.add(usernameField);
        container.add(passwordField);
        container.add(create);
        container.add(secret);
        container.add(secretPasswordField);
        container.add(back);

        create.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create) {

            if(String.valueOf(secretPasswordField.getPassword()).equals("cpk")) {
                if (BankSystem.getManagers().isEmpty()) {
                    new Manager(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                } else {
                    boolean flag = false;
                    for (Manager manager : BankSystem.getManagers()) {
                        if (usernameField.getText().equals(manager.getUsername())) {
                            JOptionPane.showMessageDialog(this, "This username already exists.");
                            flag = true;
                        }
                    }

                    if(!flag) {
                        new Manager(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                        JOptionPane.showMessageDialog(this, "Account created successfully");
                    }
                }
            } else {
                if(BankSystem.getCustomers().isEmpty()) {
                    new Customer(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                } else {
                    boolean flag = false;
                    for (Customer customer : BankSystem.getCustomers()) {
                        if (usernameField.getText().equals(customer.getUsername())) {
                            JOptionPane.showMessageDialog(this, "This username already exists.");
                            flag = true;
                        }
                    }

                    if(!flag) {
                        new Customer(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                        JOptionPane.showMessageDialog(this, "Account created successfully");
                    }
                }
            }
        } else if (e.getSource() == back) {
            LoginPage frame = new LoginPage();
            frame.setTitle("Login Page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
