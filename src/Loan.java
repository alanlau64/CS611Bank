import java.util.Date;

public class Loan {
    private Currency currency;
    private Double amount;
    private static Double loanInterest = Constant.LOAN_INTEREST;
    private String mortgage;
    private boolean isVerify;
    private int loanNum;
    private Date overdueTime;
    private String userName;

    public Loan(){}

    public Loan(Currency currency, Double amount, String mortgage, Date overdueTime, String userName){
        this.currency = currency;
        this.amount = amount;
        this.mortgage = mortgage;
        this.isVerify = false;
        this.loanNum = ++Constant.MAX_LOAN_NUMBER;
        this.overdueTime = overdueTime;
        this.userName = userName;
    }

    // pay the loan with given account and amount of money
    public Double payBack(Account account, Double amount){
        Double actualPayBack = Math.min(amount, this.amount);
        if(actualPayBack > account.getBalance().get(currency))
            return null;
        else {
            account.getBalance().put(currency, account.getBalance().get(currency) - actualPayBack);
            this.amount -= actualPayBack;
            return this.amount;
        }
    }

    // when the loan is verified, add the interest to the amount
    public Double ChargeInterest(){
        int duringDay = (int) (System.currentTimeMillis() - overdueTime.getTime()) / (1000 * 60 * 60 * 24);
        amount += amount * loanInterest * duringDay;
        return amount;
    }

    // check the loan whether is overdue or not, if yes, confiscate the mortgage
    public boolean checkOverDue(){
        return Constant.CURRENT_TIME.before(overdueTime);
    }

    @Override
    public boolean equals(Object obj) {
        return this.loanNum == ((Loan) obj).getLoanNum();
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

    public boolean getIsVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public int getLoanNum() {
        return loanNum;
    }
}
