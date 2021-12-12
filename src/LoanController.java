public class LoanController {
    private Loan loan;

    public LoanController(){}

    public LoanController(Loan loan){
        this.loan = loan;
    }

    // pay the loan with given account and amount of money
    public Double payBack(Account account, Double amount){
        Double actualPayBack = Math.min(amount, loan.getAmount());
        if(actualPayBack > account.getBalance().get(loan.getCurrency()))
            return null;
        else {
            account.getBalance().put(loan.getCurrency(), account.getBalance().get(loan.getCurrency()) - actualPayBack);
            loan.setAmount(loan.getAmount() - actualPayBack);
            return loan.getAmount();
        }
    }

    // when the loan is verified, add the interest to the amount
    public Double ChargeInterest(){
        int duringDay = (int) (loan.getOverdueTime().getTime() - Constant.CURRENT_TIME.getTime()) / (1000 * 60 * 60 * 24);
        loan.setAmount(loan.getAmount() + loan.getAmount() * Loan.getLoanInterest() * duringDay);
        return loan.getAmount();
    }

    // check the loan whether is overdue or not, if yes, confiscate the mortgage
    public boolean checkOverDue(){
        return Constant.CURRENT_TIME.before(loan.getOverdueTime());
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
