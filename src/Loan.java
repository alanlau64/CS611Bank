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
    private Account inAccount;

    public Loan(){}

    public Loan(Currency currency, Double amount, String mortgage, Date overdueTime, String userName, Account inAccount){
        this.currency = currency;
        this.amount = amount;
        this.mortgage = mortgage;
        this.isVerify = false;
        this.loanNum = ++Constant.MAX_LOAN_NUMBER;
        this.overdueTime = overdueTime;
        this.userName = userName;
        this.inAccount = inAccount;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public static Double getLoanInterest() {
        return loanInterest;
    }

    public String getUserName() {
        return userName;
    }

    public String getMortgage() {
        return mortgage;
    }

    public Account getInAccount() {
        return inAccount;
    }
}
