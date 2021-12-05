import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<CheckingAccount> checkings;
    private ArrayList<SavingAccount> savings;

    public Customer(){}

    public Customer(String name, String password){
        super(name,password);
        this.checkings = new ArrayList<>();
        this.savings = new ArrayList<>();
    }

    public void openCheckingAccount(){
        checkings.add(new CheckingAccount());
    }

    public void openSavingAccount(){
        savings.add(new SavingAccount());
    }

    public void closeCheckingAccount(Account account){
        checkings.remove((CheckingAccount) account);
    }

    public void closeSavingAccount(Account account){
        savings.remove((SavingAccount) account);
    }
}
