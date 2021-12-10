public class SavingAccount extends Account{
    public SavingAccount(){}

    public SavingAccount(String userName){
        super(userName);
        withdrawTransaction = new WithdrawWithTransactionFee();
        depositTransaction = new DepositWithoutTransactionFee();
        transferTransaction = new TransferWithoutTransactionFee();
    }
}
