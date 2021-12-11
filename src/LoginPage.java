import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class LoginPage extends JFrame implements ActionListener {

    private Container container;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JLabel username;
    private JLabel password;

    private JButton login;
    private JButton create;

    public LoginPage() {
        container = getContentPane();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        username = new JLabel("Username");
        password = new JLabel("Password");
        login = new JButton("Login");
        create = new JButton("Create Account");
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        username.setBounds(50, 150, 100, 30);
        password.setBounds(50, 220, 100, 30);
        login.setBounds(50, 300, 100, 30);
        create.setBounds(200, 300, 100, 30);

        container.add(username);
        container.add(password);
        container.add(usernameField);
        container.add(passwordField);
        container.add(login);
        container.add(create);

        create.addActionListener(this);
        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create) {
            CreateAccountPage frame = new CreateAccountPage();
            frame.setTitle("Create Account");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        } else if (e.getSource() == login) {
            String username = usernameField.getText();
            String password = Arrays.toString(passwordField.getPassword());

            for(Customer customer : BankSystem.getCustomers()) {

                if(username.equals(customer.getUsername())) {
                    if(password.equals(customer.getPassword())) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        CustomerHomePage frame = new CustomerHomePage(customer);
                        frame.setTitle(username + "'s home page");
                        frame.setVisible(true);
                        frame.setBounds(10, 10, 370, 700);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setResizable(false);

                        dispose();
                        frame.showPage();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "Username/Password is wrong.");
                    }
                }
            }

            for(Manager manager : BankSystem.getManagers()) {

                if(username.equals(manager.getUsername())) {
                    if(password.equals(manager.getPassword())) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        ManagerHomePage frame = new ManagerHomePage(manager);
                        frame.setTitle(username + "'s home page");
                        frame.setVisible(true);
                        frame.setBounds(10, 10, 370, 700);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setResizable(false);

                        dispose();
                        frame.showPage();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "Username/Password is wrong.");
                    }
                }
            }
        }

    }
}
