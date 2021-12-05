public class CheckingAccount extends Account{
    public CheckingAccount(){
        super();
        withdrawTransaction = new WithdrawWithTransactionFee(this);
        depositTransaction = new DepositWithTransactionFee(this);
        transferTransaction = new TransferWithTransactionFee(this);
    }
}
