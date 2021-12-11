import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("Duruvan", "pass");

        //CustomerHomePage frame = new CustomerHomePage(customer);
        //OpenAccountPage frame = new OpenAccountPage(customer);
        //ManagerHomePage frame = new ManagerHomePage(new Manager());
        LoginPage frame = new LoginPage();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.showPage();

    }
}
