public class SecuritiesAccount extends Account{
    public SecuritiesAccount(){
        super();
        withdrawTransaction = new WithdrawWithTransactionFee(this);
        depositTransaction = new DepositWithoutTransactionFee(this);
        transferTransaction = new TransferWithoutTransactionFee(this);
    }
}
