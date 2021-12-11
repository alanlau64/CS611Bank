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

    private JLabel username;
    private JLabel password;

    private JButton create;

    public CreateAccountPage() {
        container = getContentPane();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        username = new JLabel("Username");
        password = new JLabel("Password");
        create = new JButton("Create Account");
        //all page have to contain this close object
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        username.setBounds(50, 150, 100, 30);
        password.setBounds(50, 220, 100, 30);
        create.setBounds(50, 300, 100, 30);

        container.add(username);
        container.add(password);
        container.add(usernameField);
        container.add(passwordField);
        container.add(create);

        create.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create) {
            User user = new User(usernameField.getText(), Arrays.toString(passwordField.getPassword()));
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
