import java.util.ArrayList;
import java.util.Date;

public class CustomerController {
    private Customer customer;

    public CustomerController(){}

    public CustomerController(Customer customer){
        this.customer = customer;
    }

    public void openCheckingAccount(){
        customer.getCheckings().add(new CheckingAccount(customer.getUsername()));
    }

    public void openSavingAccount(){
        customer.getSavings().add(new SavingAccount(customer.getUsername()));
    }

    public boolean closeCheckingAccount(Account account){
        for(Double amount : account.getBalance().values())
            if(amount != 0)
                return false;
        customer.getCheckings().remove((CheckingAccount) account);
        return true;
    }

    public boolean closeSavingAccount(Account account){
        for(Double amount : account.getBalance().values())
            if(amount != 0)
                return false;
        customer.getSavings().remove((SavingAccount) account);
        return true;
    }

    public boolean openSecuritiesAccount(){
        for(SavingAccount savingAccount: customer.getSavings()){
            if(savingAccount.getBalance().containsKey(Currency.USD) &&
                    savingAccount.getBalanceWithCurrency(Currency.USD) > Constant.OPEN_SECURITIES_THRESHOLD){
                customer.setSecuritiesAccount(new SecuritiesAccount(customer.getUsername()));
                return true;
            }
        }
        // if there is no saving account having balance more than threshold, open fail
        return false;
    }

    public boolean closeSecuritiesAccount(){
        for(Double amount : customer.getSecuritiesAccount().getBalance().values())
            if(amount != 0)
                return false;
        for(Integer amount : customer.getSecuritiesAccount().getStocks().values())
            if(amount != 0)
                return false;
        customer.setSecuritiesAccount(null);
        return true;
    }

    //TODO: add log
    public Loan requestLoan(Currency currency, Double amount, String mortgage, Date overdueTime){
        Loan newLoan = new Loan(currency, amount, mortgage, overdueTime, customer.getUsername());
        customer.getLoans().add(newLoan);
        return newLoan;
    }

    //when the loan is verified by manager, the customer could get the money
    //TODO: add log
    public void createLoan(Account account, Loan loan){
        new AccountController(account).deposit(loan.getCurrency(),loan.getAmount());
    }

    //TODO: add log
    public Double payBackLoan(Account account, Double amount, Loan loan){
        if(loan.getIsVerify()) {
            Double leftAmount = new LoanController(loan).payBack(account, amount);
            if (leftAmount != null && leftAmount == 0) {
                //log the loan has been paid back
                customer.getLoans().remove(loan);
            }
            //return will be null when the given account doesn't have enough balance
            return leftAmount;
        }
        //if the loan is not verified by manager, return null
        else return null;
    }

    //TODO: log confiscate the mortgage
    public void checkLoanOverdue(Loan loan){
        if(new LoanController(loan).checkOverDue())
            customer.getLoans().remove(loan);
    }

    public ArrayList<Integer> getCheckingAccountNums() {
        ArrayList<Integer> checkingAccountNums = new ArrayList<>();

        for(Account account : customer.getCheckings()) {
            checkingAccountNums.add(account.getAccountNum());
        }

        return checkingAccountNums;
    }

    public ArrayList<Integer> getSavingAccountNums() {
        ArrayList<Integer> savingAccountNums = new ArrayList<>();

        for (Account account : customer.getSavings()) {
            savingAccountNums.add(account.getAccountNum());
        }

        return savingAccountNums;

    }

    public ArrayList<Integer> getLoansNums() {
        ArrayList<Integer> loanNums = new ArrayList<>();

        for(Loan loan: customer.getLoans()) {
            loanNums.add(loan.getLoanNum());
        }

        return loanNums;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
