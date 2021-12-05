public class WithdrawWithTransactionFee implements Withdraw{
    private Account account;

    public WithdrawWithTransactionFee(){}

    public WithdrawWithTransactionFee(Account account){
        this.account = account;
    }

    @Override
    public Double withDraw(Currency currency, double money) {
        if(account.getBalance().containsKey(currency) && account.getBalance().get(currency) >= money
                + Constant.TRANSACTION_FEE) {
            account.getBalance().put(currency, account.getBalance().get(currency) - money - Constant.TRANSACTION_FEE);
            return account.getBalance().get(currency);
        }
        else return -1.0;
    }
}
