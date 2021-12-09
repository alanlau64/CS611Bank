//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /***
        Gson gson = new Gson();
        String profile = "{\"username\": \"test\", \"password\": passwd}";
        User sampleUser = gson.fromJson(profile, User.class);
        System.out.println(sampleUser);
        User anotherUser = new Customer("test1", "password");
        String anotherProfile = gson.toJson(anotherUser);
        System.out.println(anotherProfile);

        ArrayList<User> users = new ArrayList<>();
        users.add(sampleUser);
        users.add(anotherUser);
        String usersJson = gson.toJson(users);
        System.out.println(usersJson);

        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> anotherList = gson.fromJson(usersJson, listType);
        for (User user : anotherList) {
            System.out.println(user);
        }
         ***/
        Customer customer = new Customer("Duruvan", "pass");
        Loan loan = new Loan(Currency.CNY, 100000.0, new Account());


        //CustomerHomePage frame = new CustomerHomePage(customer);
        //OpenAccountPage frame = new OpenAccountPage(customer);
        //ManagerHomePage frame = new ManagerHomePage(new Manager());
        //LoanPage frame = new LoanPage(customer);
        SingleLoanView frame = new SingleLoanView(customer, loan);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.showPage();

    }
}