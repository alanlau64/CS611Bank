import java.util.ArrayList;

public class Customer extends User{
    public ArrayList<CheckingAccount> getCheckings() {
        return checkings;
    }

    public ArrayList<SavingAccount> getSavings() {
        return savings;
    }

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
        // if there is no saving account having balance more than threshold, open fail
        return false;
    }

    public void closeSecuritiesAccount(){
        securitiesAccount = null;
    }

    public ArrayList<Integer> getCheckingAccountNums() {
        ArrayList<Integer> checkingAccountNums = new ArrayList<>();

        for(Account account : checkings) {
            checkingAccountNums.add(account.getAccountNum());
        }

        return checkingAccountNums;
    }

    public ArrayList<Integer> getSavingAccountNums() {
        ArrayList<Integer> savingAccountNums = new ArrayList<>();

        for(Account account : savings) {
            savingAccountNums.add(account.getAccountNum());
        }

        return savingAccountNums;
    }
}
