import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<CheckingAccount> checkings;
    private ArrayList<SavingAccount> savings;
    private SecuritiesAccount securitiesAccount;

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

    public boolean openSecuritiesAccount(){
        for(SavingAccount savingAccount: savings){
            if(savingAccount.getBalanceWithCurrency(Currency.USD) > Constant.OPEN_SECURITIES_THRESHOLD){
                this.securitiesAccount = new SecuritiesAccount();
                return true;
            }
        }
        return false;
    }

    public void closeSecuritiesAccount(){
        securitiesAccount = null;
    }
}
