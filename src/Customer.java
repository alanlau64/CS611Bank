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

    public ArrayList<Integer> getCheckingAccountNums() {
        ArrayList<Integer> checkingAccountNums = new ArrayList<>();

        for(Account account : checkings) {
            checkingAccountNums.add(account.getAccountNum());
        }

        return checkingAccountNums;
    }

    public ArrayList<Integer> getSavingAccountNums() {
        ArrayList<Integer> savingAccountNums = new ArrayList<>();

        for (Account account : savings) {
            savingAccountNums.add(account.getAccountNum());
        }

        return savingAccountNums;

    }

    public ArrayList<Integer> getLoansNums() {
        ArrayList<Integer> loanNums = new ArrayList<>();

        for(Loan loan: loans) {
            loanNums.add(loan.getLoanNum());
        }

        return loanNums;
     }

    public void setSecuritiesAccount(SecuritiesAccount securitiesAccount) {
        this.securitiesAccount = securitiesAccount;
    }
}
