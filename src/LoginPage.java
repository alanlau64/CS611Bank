import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        }

    }
}
