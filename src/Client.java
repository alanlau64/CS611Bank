import java.util.ArrayList;

public class Client extends User{
    private ArrayList<CheckingAccount> checkings;
    private ArrayList<SavingAccount> savings;

    public Client(){}

    public Client(String name, String password){
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

    public boolean withDraw(Account account, Currency money){
        return account.withDraw(money);
    }

    public boolean deposit(Account account, Currency money){
        return account.deposit(money);
    }

    public boolean transfer(Account out, Account in, Currency money){
        if(out.withDraw(money)){
            return false;
        }
        else in.deposit(money);
        return true;
    }
}
