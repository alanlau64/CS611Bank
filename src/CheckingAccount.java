public class CheckingAccount extends Account{
    public CheckingAccount(){}

    public CheckingAccount(String userName){
        super(userName);
        withdrawTransaction = new WithdrawWithTransactionFee();
        depositTransaction = new DepositWithTransactionFee();
        transferTransaction = new TransferWithTransactionFee();
    }
}
