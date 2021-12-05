// TODO: connect the loan with customer
public class Loan {
    private Currency currency;
    private Double amount;
    private static Double loanInterest = Constant.LOAN_INTEREST;
    private Account account;

    public Loan(){}

    public Loan(Currency currency, Double amount, Account account){
        this.currency = currency;
        this.amount = amount;
        this.account = account;
    }

    public Double payBack(Double amount){
        Double actualPayBack = Math.min(amount, this.amount);
        if(actualPayBack > account.getBalance().get(currency))
            return -1.0;
        else {
            account.getBalance().put(currency, account.getBalance().get(currency) - actualPayBack);
            this.amount -= actualPayBack;
            return this.amount;
        }
    }

    public Double ChargeInterest(){
        account.getBalance().put(currency, account.getBalance().get(currency) - amount * loanInterest);
        return amount * loanInterest;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
