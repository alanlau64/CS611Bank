import java.util.ArrayList;
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

    //TODO: add log
    public void openCheckingAccount(){
        checkings.add(new CheckingAccount(this.getUsername()));
    }

    //TODO: add log
    public void openSavingAccount(){
        savings.add(new SavingAccount(this.getUsername()));
    }

    //TODO: add log
    public void closeCheckingAccount(Account account){
        checkings.remove((CheckingAccount) account);
    }

    //TODO: add log
    public void closeSavingAccount(Account account){
        savings.remove((SavingAccount) account);
    }

    //TODO: add log
    public boolean openSecuritiesAccount(){
        for(SavingAccount savingAccount: savings){
            if(savingAccount.getBalanceWithCurrency(Currency.USD) > Constant.OPEN_SECURITIES_THRESHOLD){
                this.securitiesAccount = new SecuritiesAccount(this.getUsername());
                return true;
            }
        }
        // if there is no saving account having balance more than threshold, open fail
        return false;
    }

    //TODO: add log
    public void closeSecuritiesAccount(){
        securitiesAccount = null;
    }

    //TODO: add log
    public Loan requestLoan(Currency currency, Double amount, String mortgage, Date overdueTime){
        Loan newLoan = new Loan(currency, amount, mortgage, overdueTime, this.getUsername());
        loans.add(newLoan);
        return newLoan;
    }

    //when the loan is verified by manager, the customer could get the money
    //TODO: add log
    public void createLoan(Account account, Loan loan){
        account.deposit(loan.getCurrency(),loan.getAmount());
    }

    //TODO: add log
    public Double payBackLoan(Account account, Double amount, Loan loan){
        if(loan.getIsVerify()) {
            Double leftAmount = loan.payBack(account, amount);
            if (leftAmount != null && leftAmount == 0) {
                //log the loan has been paid back
                loans.remove(loan);
            }
            //return will be null when the given account doesn't have enough balance
            return leftAmount;
        }
        //if the loan is not verified by manager, return null
        else return null;
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

        for(Account account : savings) {
            savingAccountNums.add(account.getAccountNum());
        }

        return savingAccountNums;
    }
}
