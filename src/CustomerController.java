import java.util.ArrayList;
import java.util.Date;

public class CustomerController {
    private Customer customer;

    public CustomerController(){}

    public CustomerController(Customer customer){
        this.customer = customer;
    }

    public void openCheckingAccount(){
        CheckingAccount account = new CheckingAccount(customer.getUsername());
        customer.getCheckings().add(account);
        BankSystem.addAccountActivity(new AccountActivity(this.customer, account.getAccountNum(),
                Constant.CURRENT_TIME, "checking", "open"));
    }

    public void openSavingAccount(){
        SavingAccount account = new SavingAccount(customer.getUsername());
        customer.getSavings().add(account);
        BankSystem.addAccountActivity(new AccountActivity(this.customer, account.getAccountNum(),
                Constant.CURRENT_TIME, "saving", "open"));
    }

    public boolean closeCheckingAccount(Account account){
        for(Double amount : account.getBalance().values())
            if(amount != 0)
                return false;
        customer.getCheckings().remove((CheckingAccount) account);
        BankSystem.addAccountActivity(new AccountActivity(this.customer, account.getAccountNum(),
                Constant.CURRENT_TIME, "checking", "close"));
        return true;
    }

    public boolean closeSavingAccount(Account account){
        for(Double amount : account.getBalance().values())
            if(amount != 0)
                return false;
        customer.getSavings().remove((SavingAccount) account);
        BankSystem.addAccountActivity(new AccountActivity(this.customer, account.getAccountNum(),
                Constant.CURRENT_TIME, "saving", "close"));
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

    public Loan requestLoan(Currency currency, Double amount, String mortgage, Date overdueTime, Account inAccount){
        Loan newLoan = new Loan(currency, amount, mortgage, overdueTime, customer.getUsername(), inAccount);
        customer.getLoans().add(newLoan);
        BankSystem.addLoanActivity(new LoanActivity(Constant.CURRENT_TIME, newLoan, "request"));
        BankSystem.addLoan(newLoan);
        return newLoan;
    }

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

    public void checkLoanOverdue(Loan loan){
        if(new LoanController(loan).checkOverDue()) {
            BankSystem.addLoanActivity(new LoanActivity(Constant.CURRENT_TIME, loan, "expire"));
            customer.getLoans().remove(loan);
        }

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
