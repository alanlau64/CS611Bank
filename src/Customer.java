import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Customer extends User{
    private ArrayList<CheckingAccount> checkings;
    private ArrayList<SavingAccount> savings;
    private ArrayList<Loan> loans;
    private SecuritiesAccount securitiesAccount;


    public Customer(){}

    public Customer(String name, String password){
        super(name,password);
        this.checkings = new ArrayList<>();
        this.savings = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public ArrayList<CheckingAccount> getCheckings() {
        return checkings;
    }

    public ArrayList<SavingAccount> getSavings() {
        return savings;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public SecuritiesAccount getSecuritiesAccount() {
        return securitiesAccount;
    }

    public void setSecuritiesAccount(SecuritiesAccount securitiesAccount) {
        this.securitiesAccount = securitiesAccount;
    }
}
