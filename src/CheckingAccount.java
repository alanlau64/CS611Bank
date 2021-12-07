public class CheckingAccount extends Account{
    public CheckingAccount(){}

    public CheckingAccount(String userName){
        super(userName);
        withdrawTransaction = new WithdrawWithTransactionFee(this);
        depositTransaction = new DepositWithTransactionFee(this);
        transferTransaction = new TransferWithTransactionFee(this);
    }
}
