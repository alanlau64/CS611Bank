public class SavingAccount extends Account{
    public SavingAccount(){}

    public SavingAccount(String userName){
        super(userName);
        withdrawTransaction = new WithdrawWithTransactionFee(this);
        depositTransaction = new DepositWithoutTransactionFee(this);
        transferTransaction = new TransferWithoutTransactionFee(this);
    }

    // get the interest when the account having balance more than threshold
    public void getInterest(){
        for(Currency currency: this.getBalance().keySet()){
            if(this.getBalance().get(currency) > Constant.SAVING_INTEREST_THRESHOLD)
                this.getBalance().put(currency, Constant.SAVING_INTEREST_THRESHOLD +
                        (this.getBalance().get(currency) - Constant.SAVING_INTEREST_THRESHOLD)
                                * (1 + Constant.SAVING_INTEREST));
        }
    }
}
