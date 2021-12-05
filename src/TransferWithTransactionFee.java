public class TransferWithTransactionFee implements Transfer{
    private Account account;

    public TransferWithTransactionFee(){}

    public TransferWithTransactionFee(Account account){
        this.account = account;
    }

    @Override
    public Double transfer(Account in, Currency currency, double money) {
        if(account.getBalance().containsKey(currency) && account.getBalance().get(currency) >= money
                + Constant.TRANSACTION_FEE) {
            account.getBalance().put(currency, account.getBalance().get(currency) - money - Constant.TRANSACTION_FEE);
            if(in.getBalance().containsKey(currency))
                in.getBalance().put(currency, account.getBalance().get(currency) + money - Constant.TRANSACTION_FEE);
            else
                in.getBalance().put(currency, money - Constant.TRANSACTION_FEE);
            return account.getBalance().get(currency);
        }
        else return -1.0;
    }
}
